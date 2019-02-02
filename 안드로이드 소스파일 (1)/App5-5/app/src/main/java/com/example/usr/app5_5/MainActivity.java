package com.example.usr.app5_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu_main.xml ���ҽ��� �޴� �׸��� �׼� �ٿ� �߰�
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //action_search �޴� �׸��� searchItem�� ����
        MenuItem searchItem = menu.findItem(R.id.action_search);

        //searchItem �׸��� ����� �׼� �並 ����
        SearchView searchView = (SearchView) searchItem.getActionView();

        //�׼� �信 �⺻ �ؽ�Ʈ ����
        searchView.setQueryHint("name or email");

        //����ڰ� ���Ǹ� �����Ҷ� �߻��ϴ� �̺�Ʈ ó��
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            //�ؽ�Ʈ�� ����Ǹ� ȣ��
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            //���Ǹ� �ۼ��ϰ� ���͸� �Է��ϸ� ȣ��
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
