package com.example.usr.app14_3;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        Log.i("test", "lat:"+mLastLocation.getLatitude()+", lon:"+mLastLocation.getLongitude());
        if (mLastLocation != null) {
            Log.i("test", "in");
            setUpMap(mLastLocation);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("test", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("test", "onConnectionFailed");
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

    // 변경된 위치를 맵으로 출력
    public void updateLocation(Location location) {

        if (location != null) {
            setUpMap(location);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {

        // 구글맵 객체가 널인지 확인
        if (mMap == null) {

            // SupportMapFragment로 맵 객체 획득
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        }
    }

    private void setUpMap(Location location) {

        double lat = location.getLatitude();
        double lon = location.getLongitude();
        LatLng myLoc = new LatLng(lat, lon);

        //위도, 경도가 (0, 0)인 위치에 마킹
        mMap.addMarker(new MarkerOptions().position(myLoc).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}
