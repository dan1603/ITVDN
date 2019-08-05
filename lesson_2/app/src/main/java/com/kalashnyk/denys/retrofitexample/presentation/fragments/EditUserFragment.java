package com.kalashnyk.denys.retrofitexample.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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

public class EditUserFragment extends Fragment {

    UserAPI mApi;
    private CardView mEditUserCV;
    private CardView mEditResponseCV;

    public static EditUserFragment newInstance() {
        return new EditUserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_user, container, false);
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
        mEditUserCV = requireActivity().findViewById(R.id.cv_edit_user);
        mEditResponseCV = requireActivity().findViewById(R.id.cv_edit_response);
        Button getEditUserButton = requireActivity().findViewById(R.id.btn_get_edit_user);
        Button saveUserButton = requireActivity().findViewById(R.id.btn_save_user);
        EditText etID = requireActivity().findViewById(R.id.et_id_edit);
        EditText etName = requireActivity().findViewById(R.id.et_edit_name);
        EditText etSurName = requireActivity().findViewById(R.id.et_edit_surname);
        EditText etFatherName = requireActivity().findViewById(R.id.et_edit_fathername);

        getEditUserButton.setOnClickListener(v -> {
            hideCardViews();
            if (etID.getText() != null) {
                getUser(Integer.valueOf(etID.getText().toString()));
            }
            else {
                showMessage("Validation Error.");
            }
        });

        saveUserButton.setOnClickListener(v -> {
            if (etFatherName.getText() != null && etName.getText() != null && etSurName.getText() != null) {
                updateUser(Integer.valueOf(etID.getText().toString()), etName.getText().toString(), etSurName.getText().toString(), etFatherName.getText().toString());
            }
            else {
                showMessage("Validation Error.");
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
                        showMessage(e.getMessage());
                    }
                });
    }

    private void updateUser(int id, String name, String surname, String fname) {
        mApi.updateUser(id, name, surname, fname)
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

    private void showUser(User user) {
        EditText etName = requireActivity().findViewById(R.id.et_edit_name);
        EditText etSurName = requireActivity().findViewById(R.id.et_edit_surname);
        EditText etFatherName = requireActivity().findViewById(R.id.et_edit_fathername);
        etName.setText(user.getName());
        etSurName.setText(user.getSurname());
        etFatherName.setText(user.getFathername());
        mEditUserCV.setVisibility(View.VISIBLE);
    }

    private void hideCardViews() {
        if (mEditResponseCV != null) mEditResponseCV.setVisibility(View.GONE);
        if (mEditUserCV != null) mEditUserCV.setVisibility(View.GONE);
    }

    private void showMessage(String message) {
        mEditResponseCV.setVisibility(View.VISIBLE);
        TextView txtMsg = requireActivity().findViewById(R.id.txt_edit_response_msg);
        txtMsg.setText(message);
    }
}
