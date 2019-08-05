package com.kalashnyk.denys.retrofitexample.presentation.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kalashnyk.denys.retrofitexample.App;
import com.kalashnyk.denys.retrofitexample.R;
import com.kalashnyk.denys.retrofitexample.pojo.User;
import com.kalashnyk.denys.retrofitexample.server.UserAPI;
import com.kalashnyk.denys.retrofitexample.server.UserApiClient;
import com.kalashnyk.denys.retrofitexample.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class FileFragment extends Fragment {

    private static final int FILE_SELECT_CODE = 0;
    private File mFile;
    private Uri mUri;
    @Inject
    public UserAPI mApi;
    private CardView mFileResponseCV;

    public static FileFragment newInstance() {
        return new FileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency(this);
    }

    private void injectDependency(FileFragment fragment) {
        ((App) Objects.requireNonNull(getActivity()).getApplication()).getApiComponent().inject(fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_file, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    mUri = data.getData();
                    Log.d("FILE", mUri.toString());
                    mFile = new File(mUri.getPath());
                    showFileName();
                    showUploadButton();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initViews() {
        mFileResponseCV = requireActivity().findViewById(R.id.cv_file_response);
        Button selectButton = requireActivity().findViewById(R.id.btn_select_file);
        Button uploadButton = requireActivity().findViewById(R.id.btn_upload_file);

        selectButton.setOnClickListener(v -> {
            hideCardViews();
            showFileChooser();
        });

        uploadButton.setOnClickListener(v -> {
            uploadFile();
        });
    }

    private void uploadFile() {
        if (mFile != null) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(mFile.getName()), mFile);
            MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", mFile.getName(), requestBody);
            mApi.uploadFile(multipartBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<ResponseBody>() {
                        @Override
                        public void onSuccess(ResponseBody body) {
                            try {
                                showMessage("UPLOAD SUCCESS\n" + body.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            showMessage("UPLOAD FAIL\n" + e.getMessage());
                        }
                    });
        } else {
            showMessage("File is null or invalid");
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) { }
    }

    private void hideCardViews() {
        if (mFileResponseCV != null) mFileResponseCV.setVisibility(View.GONE);
        requireActivity().findViewById(R.id.btn_upload_file).setVisibility(View.GONE);
    }

    private void showFileName() {
        TextView txt = requireActivity().findViewById(R.id.txt_path);
        if (mFile != null) txt.setText(mFile.getName());
    }

    private void showUploadButton() {
        requireActivity().findViewById(R.id.btn_upload_file).setVisibility(View.VISIBLE);
    }

    private void showMessage(String msg) {
        mFileResponseCV.setVisibility(View.VISIBLE);
        TextView txt = requireActivity().findViewById(R.id.txt_file_response_msg);
        txt.setText(msg);
    }
}
