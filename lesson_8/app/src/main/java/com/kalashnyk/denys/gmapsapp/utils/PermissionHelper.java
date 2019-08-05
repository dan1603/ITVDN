package com.kalashnyk.denys.gmapsapp.utils;

import android.app.Activity;
import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import com.kalashnyk.denys.gmapsapp.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import io.reactivex.functions.Consumer;

public class PermissionHelper {

    public final static int REQUEST_CODE = 9876;

    public static void checkCameraPermission(final Activity activity, Consumer<Boolean> permissionsGranted) {
        checkCameraPermission(activity, false, permissionsGranted);
    }

    public static void checkCameraPermission(final Activity activity, boolean requestWriteReadPermission, Consumer<Boolean> permissionsGranted) {
        checkCameraPermission(activity, false, false, permissionsGranted);
    }
    public static void checkCameraPermission(final Activity activity, boolean requestWriteReadPermission, boolean needShowManuallyDialog,
                                             Consumer<Boolean> permissionsGranted) {
        if (requestWriteReadPermission) {
            checkPermission(activity, needShowManuallyDialog, permissionsGranted, Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            checkPermission(activity, needShowManuallyDialog, permissionsGranted, Manifest.permission.CAMERA);
        }
    }

    private static void checkPermission(final Activity activity, boolean needShowManuallyDialog, Consumer<Boolean> permissionsGranted,
                                        String... permissions) {
        Dexter.withActivity(activity)
                .withPermissions(permissions)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) callConsumer(permissionsGranted, true);
                        else {
                            if (report.isAnyPermissionPermanentlyDenied() && needShowManuallyDialog) showNeedManuallyEnableDialog(activity);
                            callConsumer(permissionsGranted, false);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).withErrorListener((DexterError error) -> Log.e( "DexterError", error.name())).onSameThread().check();
    }

    private static void showNeedManuallyEnableDialog(Activity activity) {
        AlertDialogHelper.showDialog(activity.getApplicationContext(),
                activity.getString(R.string.access),
                activity.getString(R.string.access_description),
                activity.getString(R.string.no),
                activity.getString(R.string.yes),
                () -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                    intent.setData(uri);
                    activity.startActivityForResult(intent, REQUEST_CODE);
                });
    }

    private static void callConsumer(Consumer<Boolean> permissionsGranted, boolean isGranted) {
        if (permissionsGranted != null) {
            try {
                permissionsGranted.accept(isGranted);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void checkDeviceDataPermission(final Activity activity, Consumer<Boolean> permissionsGranted) {
        checkPermission(activity, true, permissionsGranted, Manifest.permission.READ_PHONE_STATE);
    }
    public static void checkAccountPermission(final Activity activity, Consumer<Boolean> permissionsGranted) {
        checkPermission(activity, true, permissionsGranted, Manifest.permission.ACCOUNT_MANAGER);
    }

    public static void checkLocationPermission(final Activity activity, Consumer<Boolean> permissionsGranted) {
        checkPermission(activity, true, permissionsGranted, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    public static void checkPhonePermission(final Activity activity, Consumer<Boolean> permissionsGranted) {
        checkPermission(activity, true, permissionsGranted, Manifest.permission.CALL_PHONE);
    }
}

