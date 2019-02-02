package com.example.addrbook.sms;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.addrbook.sms.vo.SMS;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView smsList;
    public static ArrayList<SMS> list;
    private static ArrayAdapter<SMS> adapter;
    //private Intent intent;

    static{// static 초기화 블럭
        list = new ArrayList<>();
    }
    {
        //일반 member 초기화 블럭
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        smsList = (ListView) findViewById(R.id.smsList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 ,list);
        smsList.setAdapter(adapter);

        smsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final SMS s = list.get(position);
                String msg = "보낸사람 번호: "+s.getNumber()+"\n";
                msg += "메시지 내용: "+s.getMsg()+"\n";
                msg += "보낸 날짜: " + s.getTime();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("수신 메세지")
                        .setMessage(msg)
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNeutralButton("답장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext() ,writeMsg.class);
                                intent.putExtra("number", s.getNumber());
                                startActivity(intent);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    public static void changeList(){
        adapter.notifyDataSetChanged();
    }


    //옵션메뉴 설정 ===========================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"메세지 작성");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case 1:
                Intent intent = new Intent(this,writeMsg.class);
                startActivity(intent);
                break;
        }

        return true;
    }





}
