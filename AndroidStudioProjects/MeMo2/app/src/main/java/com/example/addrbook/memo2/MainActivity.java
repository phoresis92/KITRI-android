package com.example.addrbook.memo2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ListView fileList;
    private ArrayList<String> fileNames;
    private Boolean[] delCheckList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileList = (ListView) findViewById(R.id.fileList);
        File dir = getFilesDir();
        String[] arr = dir.list();
        fileNames = new ArrayList<>();
        for(String s : arr){
            fileNames.add(s);
        }
        delCheckList = new Boolean[fileNames.size()];
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, fileNames);
        fileList.setAdapter(adapter);



    }

    public void showList(){
        fileNames.clear();
        File dir = getFilesDir();
        String[] arr = dir.list();
        for(String s : arr){
            fileNames.add(s);
        }
        delCheckList = new Boolean[fileNames.size()];
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,1,0,"메모 추가");
        menu.add(0,2,0,"삭제");


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case 1://추가
                Intent intent = new Intent(this, NewMemoActivity.class);
                startActivityForResult(intent, 1);
                break;
            case 2: //삭제
                showDialog();
                showList();
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 1:

                    break;
            }
        }

    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("삭제리스트")
/*                .setMultiChoiceItems(fileNames,delCheckList, new DialogInterface.OnMultiChoiceClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                })*/
                .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String path = "/data/data/com.example.addrbook.memo2/files/";
                        for(int i = 0; i<delCheckList.length; i++){
                            if(delCheckList[i]){
                                File f = new File(path+fileNames.get(i));
                                f.delete();
                            }
                        }
                        dialog.cancel();
                    }
                });
        builder.create().show();

    }

}
