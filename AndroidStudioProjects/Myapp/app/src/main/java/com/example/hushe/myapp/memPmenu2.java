package com.example.hushe.myapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class memPmenu2 extends AppCompatActivity {

    //택스트 버튼 설정
    private EditText nameEdit;
    private EditText telEdit;
    private EditText addrEdit;
    private Button saveBtn;

    //프로필 사진 선택
    private RadioGroup profileRadio;

    //어댑터 설정
    private ListView profileList;
    private ArrayList<Member> list;
    private AddrAdapter2 adapter ;

    //컨택스트 메뉴 설정
    private static final int EDIT = 1;
    private static final int DEL = 2;
    private int index = 0;

    //멤버빈 설정
    private Member mem = null;

    //스피너 설정
    private Spinner spinner;
    private ArrayAdapter<String> aa ;
    //스피너에 출력할 목록 이름
    private String[] imgs = {"profile1","profile2","profile3"};
    //스피너 목록에서 선택시 이미지의 리소스 아이디 반환
    private int[] img_res ={R.drawable.profile, R.drawable.profile2, R.drawable.profile3};
    //사용자가 선택한 이미지의 리소스 아이디를 저장할 변수
    private int selected_img_res = 0;


    //ListView => descendantfocusability block!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_pmenu2);

        //택스트 가져오기
        nameEdit = (EditText) findViewById(R.id.nameEdit);
        telEdit = (EditText) findViewById(R.id.telEdit);
        addrEdit = (EditText) findViewById(R.id.addrEdit);

        //프로필 라디오그룹
        profileRadio = (RadioGroup) findViewById(R.id.profileRadio);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        //어댑터 설정
        profileList = (ListView) findViewById(R.id.profileList);
        list = new ArrayList<Member>();
        adapter = new AddrAdapter2(this, R.layout.item_layout2, list);
        profileList.setAdapter(adapter);

        //컨택스트 메뉴 설정
        registerForContextMenu(profileList);

        //스피너 설정
        spinner = (Spinner) findViewById(R.id.spinner);
        aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, imgs);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(memPmenu2.this, imgs[position], Toast.LENGTH_SHORT).show();
                selected_img_res = img_res[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




    // 컨택스트 메뉴 설정 ======================================================================================
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("UPDATE");
        menu.add(0, EDIT , 0, "EDIT");
        menu.add(0, DEL , 0, "DEL");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        index = menuInfo.position;


        switch(item.getItemId()){
            case EDIT:
                //editForm();
                Intent intent = new Intent(this, editFormIntent.class);
                Member m = list.get(index);
                intent.putExtra("m", m);
                startActivityForResult(intent, 1);
                break;
            case DEL:
                delForm();
                break;
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 1:
                    Member m = (Member) data.getSerializableExtra("m");
                    list.set(index, m);
                    break;
            }
            adapter.notifyDataSetChanged();
        }

    }

    private void editForm(){
        saveBtn.setText("EDIT");
        mem = list.get(index);
        nameEdit.setText(mem.getName());
        telEdit.setText(mem.getTel());
        addrEdit.setText(mem.getAddress());
    }

    private void delForm(){
        list.remove(index);
    }

    // 저장 버튼 설정 ======================================================================================

    public void saveProfile(View v){
        Button b = (Button) v;
        String[] arr = {nameEdit.getText().toString(), telEdit.getText().toString(), addrEdit.getText().toString()};
        for(String s : arr){
            if(s.equals("") || selected_img_res == 0) {
                Toast.makeText(this, "공백입력불가", Toast.LENGTH_SHORT).show();
                return;
            }
        }



        int img_ID = R.drawable.profile;
        int id =  profileRadio.getCheckedRadioButtonId();

        switch(id){
            case R.id.profile1:
                img_ID = R.drawable.profile;
                break;
            case R.id.profile2:
                img_ID = R.drawable.profile2;
                break;
            case R.id.profile3:
                img_ID = R.drawable.profile3;
                break;
        }


        mem = new Member(arr[0], arr[1], arr[2], img_ID);


        //스피너 설정
        mem.setImg_res(selected_img_res);

        if(b.getText().toString().equals("SAVE")){
            list.add(0, mem);
        }else if(b.getText().toString().equals("EDIT")){
            list.set(index, mem);
            b.setText("SAVE");
        }

        adapter.notifyDataSetChanged();
        clear();

    }

    public void clear(){
        nameEdit.setText("");
        telEdit.setText("");
        addrEdit.setText("");
        profileRadio.clearCheck();
    }



}
