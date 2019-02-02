package com.example.hushe.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hushe.myapp.Member;
import com.example.hushe.myapp.R;

import java.util.ArrayList;

public class memPmenu extends AppCompatActivity {

    private EditText name;
    private EditText tel;
    private EditText address;
    private Button save;

    private ListView lv;
    private ArrayList<Member> list;
    private AddrAdapter adapter;                            // 수정

    private static final int ADDR_EDIT = 1;
    private static final int ADDR_DEL = 2;
    private int idx;

    //========== 프로필 사진
    private RadioGroup rg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_pmenu);
        name = (EditText)findViewById(R.id.name1);
        tel = (EditText)findViewById(R.id.tel1);
        address = (EditText)findViewById(R.id.address1);
        save = (Button)findViewById(R.id.addr_save);

        lv = (ListView)findViewById(R.id.addr_lv);
        rg = (RadioGroup) findViewById(R.id.addr_rg);//프로필 사진
        list = new ArrayList<>();
        adapter = new AddrAdapter(this, R.layout.item_layout, list);                //수정
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
    }

    public void onSave(View view){
        String[] str = {name.getText().toString(), tel.getText().toString(), address.getText().toString()};
        for(String s:str){
            if(s.equals("")){
                Toast.makeText(getApplicationContext(), "모두 입력", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //=========프로필 사진
        int img_id = R.drawable.ic_launcher_foreground;
        //if(rg.isSelected()){
            int id = rg.getCheckedRadioButtonId();
            switch(id){
                case R.id.radioButton4:

                    img_id = R.drawable.profile;

                    break;
                case R.id.radioButton5:

                    img_id = R.drawable.profile2;

                    break;
                case R.id.radioButton6:

                    img_id = R.drawable.profile3;

                    break;
            }
       // }

        //===========
        if(save.getText().equals("save")){
            list.add(0, new Member(str[0], str[1], str[2], img_id));// 이미지 id 함께 넣음
        }else{
            //list.set(idx, new Member(str[0], str[1], str[2]));
            Member m = list.get(idx);
            m.setName(str[0]);
            m.setTel(str[1]);
            m.setAddress(str[2]);
            save.setText("save");
        }

        adapter.notifyDataSetChanged();
        clear();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("UPDATE");
        menu.add(0, ADDR_EDIT, 0, "addr_edit");
        menu.add(0, ADDR_DEL, 0, "addr_del");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        idx = info.position;
        switch(item.getItemId()){
            case ADDR_EDIT:
                editForm();
                break;
            case ADDR_DEL:
                del();
                break;
        }
        return true;
    }
    public void editForm(){
        Member m = list.get(idx);
        name.setText(m.getName());
        tel.setText(m.getTel());
        address.setText(m.getAddress());
        save.setText("EDIT");
    }
    public void del(){
        list.remove(idx);
        adapter.notifyDataSetChanged();
    }
    public void clear(){
        name.setText("");
        tel.setText("");
        address.setText("");
        rg.clearCheck();

    }
}
