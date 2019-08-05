package com.kalashnyk.denys.simplemvpapp.presentation.base;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toolbar;

import com.kalashnyk.denys.simplemvpapp.App;
import com.kalashnyk.denys.simplemvpapp.R;
import com.kalashnyk.denys.simplemvpapp.di.component.PresenterComponent;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.main.MainActivity;
import com.kalashnyk.denys.simplemvpapp.utils.PermissionHelper;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import java.lang.ref.WeakReference;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;

import com.google.android.gms.location.FusedLocationProviderClient;

public abstract class BaseFragment extends Fragment implements BaseContract.View {
    protected WeakReference<View> rootView;
    protected Unbinder mUnbinder;
    protected Toolbar toolbar;
    private Location mLocation = null;
    private Task<Location> mLocationTask;
    private FusedLocationProviderClient mFusedLocationClient;
    private BroadcastReceiver mNetworkStateBroadcastReceiver;

    protected void replaceFragment(Fragment fragment) {
        ((MainActivity) getActivity()).replaceFragment(fragment, true, false, true);
    }

    protected void initializeToolbar(String tittle, boolean needEnableHome) {
        toolbar = getRootView().findViewById(R.id.toolbar);
        if (toolbar != null) {
            getActivity().setActionBar(toolbar);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
            ActionBar actionBar = getActivity().getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(tittle);
                if (needEnableHome) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
                }
            }
        }
    }

    protected void onBackPressed() {
        if (getActivity() != null) getActivity().onBackPressed();
    }

    public View getRootView() {
        if (rootView == null) return null;
        return rootView.get();
    }

    public void setRootView(View rootView) {
        this.rootView = new WeakReference<>(rootView);
    }

    protected void initializeToolbar(String tittle, int drawableId) {
        toolbar = getRootView().findViewById(R.id.toolbar);
        if (toolbar != null) {
            getActivity().setActionBar(toolbar);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
            ActionBar actionBar = getActivity().getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(tittle);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(drawableId);
            }
        }
    }

    protected boolean isLogin() {
        if (getView() == null) return false;
        SharedPreferences preferences = getContext().getSharedPreferences(getView().getContext().getString(R.string.tag_shared_pref), Context.MODE_PRIVATE);
        return preferences.getBoolean(getView().getContext().getString(R.string.tag_shared), false);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDaggerDependencies();
        hideSoftKeyboard(getActivity());
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() == null) return;
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void createDaggerDependencies() {
        injectDependency(((App) getActivity().getApplication()).getPresentersComponent()); //инжект зависисмости компонента презентера
    }

    protected abstract void injectDependency(PresenterComponent presentersComponent);

    @Override
    public void onDestroy() {
        if (rootView != null) rootView.clear(); //чистим рут вью
        super.onDestroy();

    }

    protected boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        NetworkInfo WiFiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return WiFiInfo.isConnected() || mobileInfo.isConnected();
    }

    protected void addListenerStateNetWork(ConnectivityManager.OnNetworkActiveListener listener) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");

        mNetworkStateBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                listener.onNetworkActive();
            }
        };

        getActivity().registerReceiver(mNetworkStateBroadcastReceiver, filter);
    }

    public void initLocation() {
        PermissionHelper.checkLocationPermission(getActivity(), isGranted -> {
            if (isGranted) {
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
                mLocationTask = mFusedLocationClient.getLastLocation();
                mLocationTask.addOnSuccessListener(getActivity(), location -> {
                    if (location != null) mLocation = location;
                });
            }
        });

    }

    public Location getLocation() {
        return mLocation;
    }
}
