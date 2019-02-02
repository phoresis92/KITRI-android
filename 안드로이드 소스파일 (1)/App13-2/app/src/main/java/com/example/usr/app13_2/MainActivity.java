package com.example.usr.app13_2;

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
        String urlStr = "http://api.openweathermap.org/data/2.5/weather?q="+str+"&mode=xml";
        URL url;
        System.out.println(urlStr);
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

                // factory ��ü ����
                DocumentBuilderFactory factory =
                        DocumentBuilderFactory.newInstance();

                // DOM ��ü ������ ���� builder ����
                DocumentBuilder builder = factory.newDocumentBuilder();

                // DOM ��ü ����
                Document dom = builder.parse(in);

                // document element�� �о��
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
