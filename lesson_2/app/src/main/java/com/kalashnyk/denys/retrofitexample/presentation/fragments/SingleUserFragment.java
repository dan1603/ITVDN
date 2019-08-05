package com.kalashnyk.denys.retrofitexample.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kalashnyk.denys.retrofitexample.R;
import com.kalashnyk.denys.retrofitexample.pojo.User;
import com.kalashnyk.denys.retrofitexample.server.UserAPI;
import com.kalashnyk.denys.retrofitexample.server.UserApiClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SingleUserFragment extends Fragment {

    UserAPI mApi;
    CardView mUserCV;
    CardView mErrorCV;

    public static SingleUserFragment newInstance() {
        return new SingleUserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildApi();
        initViews();
    }

    private void buildApi() {
        mApi = UserApiClient.getClient();
    }

    private void initViews() {
        mUserCV = requireActivity().findViewById(R.id.cv_single_user);
        mErrorCV = requireActivity().findViewById(R.id.cv_single_error);
        Button getUserButton = requireActivity().findViewById(R.id.btn_get_user);
        EditText etID = requireActivity().findViewById(R.id.et_id);
        getUserButton.setOnClickListener(v -> {
            hideCardViews();
            if (etID.getText() != null && TextUtils.isDigitsOnly(etID.getText().toString())) {
                getUser(Integer.valueOf(etID.getText().toString()));
            }
            else {
                showError("Validation error. Try to enter your number again.");
            }
        });
    }

    private void getUser(int id) {
        mApi.getUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(User user) {
                        showUser(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e.getMessage());
                    }
                });
    }

    private void showUser(User user) {
        mUserCV.setVisibility(View.VISIBLE);

        TextView txtName = requireActivity().findViewById(R.id.txt_name_single);
        TextView txtSurname = requireActivity().findViewById(R.id.txt_surname_single);
        TextView txtFathername = requireActivity().findViewById(R.id.txt_fathername_single);

        txtName.setText(user.getName());
        txtSurname.setText(user.getSurname());
        txtFathername.setText(user.getFathername());
    }

    private void hideCardViews() {
        if (mUserCV != null) { mUserCV.setVisibility(View.GONE); }
        if (mErrorCV != null) { mErrorCV.setVisibility(View.GONE); }
    }

    private void showError(String errorMessage) {
        TextView txtError = requireActivity().findViewById(R.id.txt_error);
        mErrorCV.setVisibility(View.VISIBLE);
        txtError.setText(errorMessage);
    }
}
