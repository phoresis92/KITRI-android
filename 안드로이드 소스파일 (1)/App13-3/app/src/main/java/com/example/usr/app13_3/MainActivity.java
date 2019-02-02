package com.example.usr.app13_3;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class MainActivity extends Activity {

    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    // ��ư Ŭ�� �� ȣ��Ǵ� �޼���
    public void show(View view) {
        String str = et.getText().toString();

        // �ڿ��� url
        String urlStr = "http://api.openweathermap.org/data/2.5/weather?q="+str;
        URL url;
        char[] data = new char[255];
        StringBuffer sb = new StringBuffer();

        try {
            url = new URL(urlStr);

            // url�� Ŀ�ؼ� ����
            URLConnection connection = url.openConnection();

            // HTTP �������ݿ� �����ϰ� HttpURLConnection�� ĳ����
            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            // ������¸� üũ�Ͽ� ������ ���� ������ ó��
            int resCode = httpConnection.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {

                // �ڿ����� �����͸� ���� �� �ֵ��� InputStream�� ��´�.
                InputStream in = httpConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                while(isr.read(data)>0) {
                    sb.append(data);
                    if(in.available() < 255){
                        for(int i=0 ; i<data.length ; i++){
                            data[i] = ' ';
                        }
                    }
                }
                System.out.println(sb);
                isr.close();
                JSONObject weatherInfo = new JSONObject(sb.toString());
                JSONObject coord = weatherInfo.getJSONObject("coord");
                JSONObject main = weatherInfo.getJSONObject("main");
                String name = weatherInfo.getString("name");
                Coord c = new Coord(coord.getDouble("lon"), coord.getDouble("lat"));
                Main m = new Main(main.getDouble("temp"), main.getDouble("pressure"), main.getDouble("humidity"));
                WeatherInfo w = new WeatherInfo(name, c, m);
                tv.setText(w.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
