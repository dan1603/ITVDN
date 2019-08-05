package com.kalashnyk.denys.gmapsapp.presentation.activities.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.kalashnyk.denys.gmapsapp.R;
import com.kalashnyk.denys.gmapsapp.di.component.ViewModelComponent;
import com.kalashnyk.denys.gmapsapp.domain.AllPinsViewModel;
import com.kalashnyk.denys.gmapsapp.presentation.adapter.PinPagerAdapter;
import com.kalashnyk.denys.gmapsapp.presentation.base.BaseActivity;
import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;
import com.kalashnyk.denys.gmapsapp.utils.PermissionHelper;
import com.kalashnyk.denys.gmapsapp.utils.directionhelpers.FetchURL;
import com.kalashnyk.denys.gmapsapp.utils.directionhelpers.TaskLoadedCallback;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements
        TaskLoadedCallback,
        LocationListener,
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleApiClient.ConnectionCallbacks {

    @Inject
    protected AllPinsViewModel mViewModel;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationManager mLocationManager;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private List<Marker> mPins = new ArrayList<>();
    private Polyline mCurrentPolyline;
    private ViewPager mPager;
    private Boolean mIsLocationEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        initMap();
        initRoute();
        initCurrentLocation();
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    private void initViewModel() {
        mViewModel.getAllItems();
        mViewModel.getLiveDataItems().observe(this, this::initViewPager);
    }

    private void initViewPager(List<PinEntity> pins) {
        mPager = findViewById(R.id.vp_pins);
        PinPagerAdapter adapter = new PinPagerAdapter(this, pins);
        mPager.setAdapter(adapter);
        IndefinitePagerIndicator indicator = findViewById(R.id.vp_indicator);
        indicator.attachToViewPager(mPager);
        for (int i = 0; i < pins.size(); i++) {
            mPins.add(addMarker(
                    new LatLng(
                            Double.valueOf(pins.get(i).getLat()),
                            Double.valueOf(pins.get(i).getLng())),
                    pins.get(i).getTitle()
            ));
        }
        if (mPins != null) { navigateToPin(mPins.get(0)); }
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                navigateToPin(mPins.get(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initRoute() {
        Button btnRoute = findViewById(R.id.btn_route);
        btnRoute.setOnClickListener(v -> {
            new FetchURL(this).execute(getUrl(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()),
                    mPins.get(mPager.getCurrentItem()).getPosition(), "driving"), "driving");
        });
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String mode = "mode=" + directionMode;
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_secret_api_key);
        return url;
    }

    private void initCurrentLocation() {
        mLocationManager = (LocationManager) this.getSystemService(AppCompatActivity.LOCATION_SERVICE);
        mIsLocationEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void initMap() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.maps_fragment);
        supportMapFragment.getMapAsync(this);
    }

    private Marker addMarker(LatLng latLng, String title) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(title);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        return mMap.addMarker(markerOptions);
    }

    private void navigateToPin(Marker pin) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pin.getPosition(), 15f));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnMarkerClickListener(this);
        PermissionHelper.checkLocationPermission(this, isGranted -> {
                    if (isGranted) {
                        initGoogleApiClient();
                        mMap.setMyLocationEnabled(true);
                    }
                }
        );
    }

    private synchronized void initGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this.getBaseContext())
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (!mIsLocationEnabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mPager.setCurrentItem(mPins.indexOf(marker), true);
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (mCurrentPolyline != null) {
            mCurrentPolyline.remove();
        }
        mCurrentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

    @Override
    public void onConnectionSuspended(int i) { }
}
