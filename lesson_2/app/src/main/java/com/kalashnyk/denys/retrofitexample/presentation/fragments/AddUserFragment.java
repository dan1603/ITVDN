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

public class AddUserFragment extends Fragment {

    UserAPI mApi;
    private CardView mAddResponseCV;

    public AddUserFragment() { }

    public static AddUserFragment newInstance() {
        return new AddUserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        buildApi();
    }

    private void buildApi() {
        mApi = UserApiClient.getClient();
    }

    private void initViews() {
        mAddResponseCV = requireActivity().findViewById(R.id.cv_add_response);
        Button getAddButton = requireActivity().findViewById(R.id.btn_add_user);
        EditText etName = requireActivity().findViewById(R.id.et_enter_name);
        EditText etSurName = requireActivity().findViewById(R.id.et_enter_surname);
        EditText etFatherName = requireActivity().findViewById(R.id.et_enter_fathername);

        getAddButton.setOnClickListener(v -> {
            hideCardViews();
            if (etFatherName.getText() != null && etName.getText() != null && etSurName.getText() != null) {
                addUser(etName.getText().toString(), etSurName.getText().toString(), etFatherName.getText().toString());
            }
            else {
                showMessage("Validation Error.");
            }
        });
    }

    private void addUser(String name, String surname, String fname) {
        mApi.addUser(name, surname, fname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String response) {
                        showMessage(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMessage(e.getMessage());
                    }
                });
    }

    private void hideCardViews() {
        if (mAddResponseCV != null) mAddResponseCV.setVisibility(View.GONE);
    }

    private void showMessage(String message) {
        mAddResponseCV.setVisibility(View.VISIBLE);
        TextView txtMsg = requireActivity().findViewById(R.id.txt_add_response_msg);
        txtMsg.setText(message);
    }
}
