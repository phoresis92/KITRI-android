package com.example.usr.nettest;


import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Main2Activity extends Activity {
    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    // 버튼 클릭 시 호출되는 메서드
    public void show(View view) {
        String str = et.getText().toString();

        // 자원의 url
        String urlStr = "http://api.openweathermap.org/data/2.5/weather?q="+str+"&mode=xml";
        URL url;
        System.out.println(urlStr);
        try {
            url = new URL(urlStr);

            // url에 커넥션 수립
            URLConnection connection = url.openConnection();

            // HTTP 프로토콜에 적합하게 HttpURLConnection로 캐스팅
            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            // 연결상태를 체크하여 정상일 때만 데이터 처리
            int resCode = httpConnection.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {

                // 자원에서 데이터를 읽을 수 있도록 InputStream을 얻는다.
                InputStream in = httpConnection.getInputStream();

                // factory 객체 생성
                DocumentBuilderFactory factory =
                        DocumentBuilderFactory.newInstance();

                // DOM 객체 생성을 위한 builder 생성
                DocumentBuilder builder = factory.newDocumentBuilder();

                // DOM 객체 생성
                Document dom = builder.parse(in);

                // document element를 읽어옴
                Element element = dom.getDocumentElement();

                Element cityTag = (Element) element.getElementsByTagName("city").item(0);
                String city = cityTag.getAttribute("name");

                Element temperatureTag = (Element) element.getElementsByTagName("temperature").item(0);
                String temperature = temperatureTag.getAttribute("value");

                Element humidityTag = (Element) element.getElementsByTagName("humidity").item(0);
                String humidity = humidityTag.getAttribute("value") +
                        humidityTag.getAttribute("unit");

                Element cloudsTag = (Element) element.getElementsByTagName("clouds").item(0);
                String clouds = cloudsTag.getAttribute("name");

                WeatherInfo w = new WeatherInfo(city, temperature, humidity, clouds);
                System.out.println(w);
                tv.setText(w.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }


}
