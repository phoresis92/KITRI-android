package com.example.addrbook.phonebook;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import vo.Member;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView phoneList;
    private ArrayList<Member> list;
    private AddrAdapter2 adapter;

    private int idx;
    private int _id;

    private PhoneBookDBA dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dba = new PhoneBookDBA(this);

        phoneList = (ListView) findViewById(R.id.phoneList);
        list = new ArrayList<>();
        adapter = new AddrAdapter2(this, R.layout.item_layout2 ,list);
        phoneList.setAdapter(adapter);

        registerForContextMenu(phoneList);

        getList();

    }
//옵션메뉴 ===============================================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"연락처 추가");
        menu.add(0,2,0,"검색");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case 1:
                Intent intent = new Intent(this, NewItemActivity.class);
                startActivityForResult(intent, 1);
                break;
            case 2:

                showDlg();

                break;
        }

        return true;
    }


    public void showDlg(){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        final RadioGroup rg = dialog.findViewById(R.id.rg);
        final EditText contentS = dialog.findViewById(R.id.contentS);
        Button searchBtn = dialog.findViewById(R.id.searchBtn);
        Button closeBtn = dialog.findViewById(R.id.closeBtn);
        final TextView resultS = dialog.findViewById(R.id.resultS);
        final ImageView image = dialog.findViewById(R.id.image);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = contentS.getText().toString();
                Member m = new Member();
                if(str.equals("")){
                    Toast.makeText(MainActivity.this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.idS:
                        try{
                            m.set_id(Integer.parseInt(str));
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, "숫자를 입력해 주세요", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                    case R.id.nameS:
                        m.setName(str);
                        break;
                    case R.id.telS:
                        m.setTel(str);
                        break;
                }
                dba.open();
                Cursor c = dba.select(m, rg.getCheckedRadioButtonId());
                if(c.moveToFirst()){
                    String x = "id: "+c.getInt(0);
                    x+= "\nname: " + c.getString(1);
                    x+= "\ntel: " + c.getString(2);
                    resultS.setText(x);
                    image.setImageResource(c.getInt(3));
                }else{
                    Toast.makeText(MainActivity.this, "결과값이 없습니다.", Toast.LENGTH_SHORT).show();
                }
                contentS.setText("");
            }
        });
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    };



//컨택스트 메뉴 ===============================================================================================

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,1,0,"수정");
        menu.add(0,2,0,"삭제");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        idx = info.position;
        switch(item.getItemId()){
            case 1:

                _id = list.get(idx).get_id();
                Intent intent = new Intent(this, NewItemActivity.class);
                intent.putExtra("m", list.get(idx));
                startActivityForResult(intent, 2);

                break;
            case 2:

                //list.remove(idx);

                dba.open();
                dba.removeData(list.get(idx).get_id());
                dba.close();

                getList();
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Member m = null;

        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 1:
                    m = (Member) data.getSerializableExtra("m");
                    dba.open();
                    dba.insertData(m.getName(), m.getTel(), m.getImg_res());
                    dba.close();

                    break;
                case 2:
                    m = (Member) data.getSerializableExtra("m");

                    dba.open();
                    dba.updateData(_id,m.getName(),m.getTel(),m.getImg_res());
                    dba.close();

                    break;
            }
            getList();
        }

    }//onActivityResult

    public void getList(){

        list.clear();

        dba.open();
        Cursor c = dba.getAll();

        if(c.moveToFirst()){
            do{
                int id = c.getInt(0);
                System.out.println(id);
                String name = c.getString(1);
                System.out.println(name);
                String tel = c.getString(2);
                System.out.println(tel);
                int img_res = c.getInt(3);
                System.out.println(img_res);
                Member m = new Member(id, name,tel,img_res);
                list.add(m);
            }while(c.moveToNext());

        }
        dba.close();
        adapter.notifyDataSetChanged();
    }//getList

}
