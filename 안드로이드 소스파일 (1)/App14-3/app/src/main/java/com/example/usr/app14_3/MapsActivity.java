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

    // ��ġ�� ��ȭ�� �����ϴ� ������
    private final LocationListener locationListener = new LocationListener() {

        // ��ġ ��ȭ�� ȣ��
        @Override
        public void onLocationChanged(Location location) {
            updateLocation(location);
        }

        // provider�� ���Ұ� �����϶� ȣ��
        @Override
        public void onProviderDisabled(String provider) {
        }

        // provider�� ��밡�� �����϶� ȣ��
        @Override
        public void onProviderEnabled(String provider) {
        }

        // provider�� ���°� ���Ҷ� ȣ��
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

    };

    // ����� ��ġ�� ������ ���
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

        // ���۸� ��ü�� ������ Ȯ��
        if (mMap == null) {

            // SupportMapFragment�� �� ��ü ȹ��
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        }
    }

    private void setUpMap(Location location) {

        double lat = location.getLatitude();
        double lon = location.getLongitude();
        LatLng myLoc = new LatLng(lat, lon);

        //����, �浵�� (0, 0)�� ��ġ�� ��ŷ
        mMap.addMarker(new MarkerOptions().position(myLoc).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}
