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

import com.kalashnyk.denys.retrofitexample.App;
import com.kalashnyk.denys.retrofitexample.R;
import com.kalashnyk.denys.retrofitexample.pojo.User;
import com.kalashnyk.denys.retrofitexample.server.UserAPI;
import com.kalashnyk.denys.retrofitexample.server.UserApiClient;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DeleteUserFragment extends Fragment {

    @Inject
    public UserAPI mApi;
    private CardView mDeleteResponseCV;

    public static DeleteUserFragment newInstance() {
        return new DeleteUserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency(this);
    }

    private void injectDependency(DeleteUserFragment fragment) {
        ((App) Objects.requireNonNull(getActivity()).getApplication()).getApiComponent().inject(fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delete_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        mDeleteResponseCV = requireActivity().findViewById(R.id.cv_delete_response);
        Button deleteUserButton = requireActivity().findViewById(R.id.btn_delete_user);
        EditText etID = requireActivity().findViewById(R.id.et_id_delete);

        deleteUserButton.setOnClickListener(v -> {
            hideCardViews();
            if (etID.getText() != null) {
                deleteUser(Integer.valueOf(etID.getText().toString()));
            } else {
                showMessage("Validation Error.");
            }
        });
    }

    private void deleteUser(int id) {
        mApi.deleteUserById(id)
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
        if (mDeleteResponseCV != null) mDeleteResponseCV.setVisibility(View.GONE);
    }

    private void showMessage(String msg) {
        mDeleteResponseCV.setVisibility(View.VISIBLE);
        TextView txt = requireActivity().findViewById(R.id.txt_delete_response_msg);
        txt.setText(msg);
    }
}
