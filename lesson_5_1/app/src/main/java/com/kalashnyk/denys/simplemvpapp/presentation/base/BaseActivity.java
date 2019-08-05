package com.kalashnyk.denys.simplemvpapp.presentation.base;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toolbar;

import com.kalashnyk.denys.simplemvpapp.App;
import com.kalashnyk.denys.simplemvpapp.R;
import com.kalashnyk.denys.simplemvpapp.di.component.PresenterComponent;
import com.kalashnyk.denys.simplemvpapp.utils.PermissionHelper;
import com.kalashnyk.denys.simplemvpapp.utils.local_helper.ILocaleHelper;
import com.kalashnyk.denys.simplemvpapp.utils.local_helper.LocaleHelperImpl;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.Unbinder;
import io.reactivex.functions.Action;

public abstract class BaseActivity extends Activity implements BaseContract.View {
    protected Unbinder unbinder;
    protected Toolbar toolbar;
    private Location mLocation = null;
    private Task<Location> mLocationTask;
    private FusedLocationProviderClient mFusedLocationClient;
    private ILocaleHelper mILocaleHelper;

    @Override
    protected void attachBaseContext(Context base) {
        mILocaleHelper = new LocaleHelperImpl();
        super.attachBaseContext(mILocaleHelper.setLocale(base));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDaggerDependencies();
        hideSoftKeyboard();
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() == null)
            return;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void createDaggerDependencies() {
        injectDependency(((App) getApplication()).getPresentersComponent());
    }

    protected abstract void injectDependency(PresenterComponent presentersComponent);

    @Override
    protected void onDestroy() {
        if (unbinder != null) unbinder.unbind();
        super.onDestroy();

    }

    public void showSoftKeyboard() {
        if (getCurrentFocus() == null) return;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) inputMethodManager.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
    }

    protected void initializeToolbar(String tittle, boolean closeVisible) {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setActionBar(toolbar);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(tittle);
                if (closeVisible) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
                }
            }
        }
    }

    protected void initializeToolbarWithCallback(String tittle, boolean closeVisible, Action action) {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setActionBar(toolbar);
            toolbar.setNavigationOnClickListener(v -> onClosePressed(action));
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(tittle);
                if (closeVisible) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
                }
            }
        }
    }

    private void onClosePressed(Action onPositiveClick) {
        if (onPositiveClick != null) {
            try {
                onPositiveClick.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (onBackPressed(fm)) return;
        hideSoftKeyboard();
        super.onBackPressed();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    private boolean onBackPressed(FragmentManager fm) {
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStackImmediate();
            return true;
        }
        return false;
    }

    protected void disableTextInTitle() {
        if (getActionBar() != null) getActionBar().setDisplayShowTitleEnabled(false);
    }

    protected void initializeToolbar(String tittle, int drawableId, boolean clickEnabled) {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setActionBar(toolbar);
            if (clickEnabled)
                toolbar.setNavigationOnClickListener(v -> onBackPressed());
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                if (!TextUtils.isEmpty(tittle))
                    actionBar.setTitle(tittle);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(drawableId);
            }
        }
    }

    protected int getColorCustom(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, true, true);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, boolean moveOnRight) {
        replaceFragment(fragment, addToBackStack, true, moveOnRight);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, boolean needAnimate, boolean moveOnRight) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        String fragmentName = fragment.getClass().getSimpleName();
        if (addToBackStack) ft = ft.addToBackStack(fragmentName);
        if (needAnimate) {
            int enterAnimation = moveOnRight ? R.animator.slide_in_left : R.animator.pop_out_right;
            int exitAnimation = moveOnRight ? R.animator.slide_out_right : R.animator.pop_in_left;
            int popEnterAnimation = moveOnRight ? R.animator.pop_out_right : R.animator.slide_in_left;
            int popExitAnimation = moveOnRight ? R.animator.pop_in_left : R.animator.slide_out_right;
            ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation);
        }
        ft.replace(R.id.container, fragment, fragmentName).commit();
    }

    public void initLocation() {
        PermissionHelper.checkLocationPermission(this, isGranted -> {
            if (isGranted) {
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                mLocationTask = mFusedLocationClient.getLastLocation();
                mLocationTask.addOnSuccessListener(this, location -> {
                    if (location != null) {
                        mLocation = location;
                    }
                });
            }
        });

    }

    public Location getLocation() {
        return mLocation;
    }

    public Task<Location> getLocationTask() {
        return mLocationTask;
    }

    protected boolean saveBitmapToFile(final Bitmap bitmap, final Bitmap.CompressFormat format, final int quality, final File file) {
        if (file == null) {
            return false;
        }
        if (file.getParent() != null && !file.isDirectory()) {
            file.mkdirs();
            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
                bitmap.compress(format, quality, outputStream);
                outputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
