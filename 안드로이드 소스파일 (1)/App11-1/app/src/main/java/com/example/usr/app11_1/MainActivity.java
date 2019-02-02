package com.example.usr.app11_1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends Activity {
    private EditText name;
    private EditText tel;
    private String name_str;
    private String tel_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //main.xml에 배치한 뷰 위젯들의 객체를 자바 코드에서 접근할 수 있도록
        //레퍼런스 정의
        name=(EditText)findViewById(R.id.editText);
        tel=(EditText)findViewById(R.id.editText2);

    }

    //에디트 텍스트를 클릭하면 호출
   public void clear(View view){
       //에디트 텍스트의 텍스트 초기화
       ((EditText)view).setText("");
   }

    //onPause()메서드는 액티비티가 다른 액티비티에 의해 가려질때 호출된다.
    @Override
    protected void onPause() {
        super.onPause();

        //프리퍼런스를 현 어플리케이션에서만 사용하도록 private 모드로 오픈
        SharedPreferences myPref = getPreferences(MODE_PRIVATE);

        //프리퍼런스에 쓰기를 하기 위해 Editor 객체 생성
        SharedPreferences.Editor editor = myPref.edit();

        //이름을 입력하는 에디트 텍스트에 입력된 값을 String 객체 name_str에 저장
        name_str=name.getText().toString();

        //name_str 값을 프리퍼런스에 'name'키로 저장
        editor.putString("name", name_str);

        //전화번호를 입력하는 에디트 텍스트에 입력된 값을 String 객체 tel_str에 저장
        tel_str=tel.getText().toString();

        //tel_str 값을 프리퍼런스에 'tel'키로 저장
        editor.putString("tel", tel_str);

        //프리퍼런스의 변경 커밋
        editor.commit();
    }

    //onResume()는 액티비티가 화면에 뜰때 호출되는 메서드
    @Override
    protected void onResume() {
        super.onResume();

        //프리퍼런스 private 모드로 오픈
        SharedPreferences myPref = getPreferences(MODE_PRIVATE);

        //프리퍼런스에 'name'키가 있으면 이 값을 읽어 이름을 입력하는 에디트
        //텍스트에 출력
        if(myPref.contains("name")){
            name.setText(myPref.getString("name", ""));
        }

        //프리퍼런스에 'tel'키가 있으면 이 값을 읽어 전화번호를 입력하는 에디트
        //텍스트에 출력
        if(myPref.contains("tel")){
            tel.setText(myPref.getString("tel", ""));
        }

        //getAll()메서드로 프리퍼런스의 모든 정보를 읽어와 맵에 저장
        Map m=myPref.getAll();

        //맵에서 사용할 이터레이터 생성
        Iterator it=m.keySet().iterator();

        //맵의 데이터 개수만큼 맵의 각 항목의 키와 값을 로그캣에 출력
        while(it.hasNext()){
            String str=(String)it.next();
            Log.i(str, (String) m.get(str));
        }
    }

}
