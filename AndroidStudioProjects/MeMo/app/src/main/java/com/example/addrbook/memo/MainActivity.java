package com.example.addrbook.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ListView memoList;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    private boolean[] delCheck;

    private static final int EDIT = 1;
    private static final int DEL = 2;

    private int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        memoList = (ListView) findViewById(R.id.memoList);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 ,list);
        memoList.setAdapter(adapter);

        getMemoList();


        registerForContextMenu(memoList);

        memoList.setClickable(true);
        memoList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "선택?", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), writeMemo.class);
                intent.putExtra("title", list.get(position));
                startActivityForResult(intent, 2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    //옵션 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"메모추가");
        menu.add(0,2,0,"삭제");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case 1:
                Intent intent = new Intent(this, writeMemo.class);
                startActivityForResult(intent, 1);
                break;
            case 2:
                showDial();

                break;
        }

        return true;
    }


    //컨택스트 메뉴 생성
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,EDIT,0,"수정");
        menu.add(0,DEL,0,"삭제");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        idx = info.position;

        switch (item.getItemId()){
            case EDIT:
                Intent intent = new Intent(this, writeMemo.class);
                intent.putExtra("title", list.get(idx));
                intent.putExtra("idx", idx);
                startActivityForResult(intent, 2);
                break;
            case DEL:
                String path = Environment.getDataDirectory()+"/data/com.example.addrbook.memo/files";

                File file = new File(path+"/"+list.get(idx));
                if(file.exists()) {
                    if (file.delete()) {
                        Toast.makeText(getApplicationContext(), "파일 삭제 성공!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "파일 삭제 실패!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "존재하지 않는 파일입니다.", Toast.LENGTH_SHORT).show();
                }

                getMemoList();
                break;
        }

        return true;
    }

    //다이얼 로그
    public void showDial(){
        int cnt = 0;
        String[] arr = new String[list.size()];
        Iterator it = list.iterator();
        while(it.hasNext()){
            arr[cnt] = (String)it.next();
            cnt++;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("삭제 리스트")
                .setMultiChoiceItems(arr, delCheck, new DialogInterface.OnMultiChoiceClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                })
                .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String path = Environment.getDataDirectory() + "/data/com.example.addrbook.memo/files/";

                        for (int i = 0; i < delCheck.length; i++) {
                            if (delCheck[i]) {
                                File f = new File(path + list.get(i));
                                f.delete();

                            }
                        }
                        getMemoList();
                    }
                });

        builder.create().show();
    }

    //결과값 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:
                    String memo = data.getStringExtra("title");
                    System.out.println(memo);
                    list.add(memo);
                    break;
                case 2:
                    String me = data.getStringExtra("title");
                    int i = data.getIntExtra("idx",0);
                    System.out.println(me);
                    list.set(i,me);
                    break;
            }
            getMemoList();
        }//if end
    }

    // 파일 리스트 가져오기
    public void getMemoList(){
        list.clear();
        String path = Environment.getDataDirectory()+"/data/com.example.addrbook.memo/files";
        File dirFile = new File(path);
        File[] fileList = dirFile.listFiles();
        for(File temp : fileList){
            if(temp.isFile()){
                list.add(temp.getName());
            }
        }
        delCheck = new boolean[list.size()];
        adapter.notifyDataSetChanged();
    }//getMemoList

}
