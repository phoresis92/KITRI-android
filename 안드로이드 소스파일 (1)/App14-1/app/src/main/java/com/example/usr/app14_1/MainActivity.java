package com.example.usr.app14_1;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);

        // 시스템으로부터 LocationManager 객체 획득
        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 지정된 provider로 위치 변화를 감지해 사용자에게 알림
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                2000, 10, locationListener);
    }

    // 위치가 변화를 감지하는 리스너
    private final LocationListener locationListener = new LocationListener() {

        // 위치 변화시 호출
        @Override
        public void onLocationChanged(Location location) {
            updateLocation(location);
        }

        // provider가 사용불가 상태일때 호출
        @Override
        public void onProviderDisabled(String provider) {
        }

        // provider가 사용가능 상태일때 호출
        @Override
        public void onProviderEnabled(String provider) {
        }

        // provider의 상태가 변할때 호출
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

    };

    // 위치를 텍스트 뷰에 출력
    public void updateLocation(Location location) {
        double lat = 0;
        double lon = 0;
        // Location객체가 널이 아니면 위도, 경도를 읽는다.
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
        }

        // 위도, 경도를 텍스트 뷰에 출력
        String str = "Latitude:" + lat + "\nLongitude:" + lon;
        tv.setText(str);
    }

}
