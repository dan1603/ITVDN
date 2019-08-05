package com.kalashnyk.denys.retrofitexample.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalashnyk.denys.retrofitexample.App;
import com.kalashnyk.denys.retrofitexample.R;
import com.kalashnyk.denys.retrofitexample.pojo.User;
import com.kalashnyk.denys.retrofitexample.pojo.UserResponse;
import com.kalashnyk.denys.retrofitexample.presentation.adapter.UserAdapter;
import com.kalashnyk.denys.retrofitexample.server.UserAPI;
import com.kalashnyk.denys.retrofitexample.server.UserApiClient;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AllUsersFragment extends Fragment {

    @Inject
    public UserAPI mApi;
    private UserAdapter mAdapter;

    public AllUsersFragment() { }

    public static AllUsersFragment newInstance() {
        return new AllUsersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency(this);
    }

    private void injectDependency(AllUsersFragment fragment) {
        ((App) Objects.requireNonNull(getActivity()).getApplication()).getApiComponent().inject(fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUsers();
    }

    private void getUsers() {
        mApi.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserResponse>() {
                    @Override
                    public void onSuccess(UserResponse users) {
                        initRecyclerView(users.getRecords());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    private void initRecyclerView(List<User> list) {
        RecyclerView rvUsers = requireActivity().findViewById(R.id.rv_users);
        LinearLayoutManager manager = new LinearLayoutManager(requireActivity());
        mAdapter = new UserAdapter(requireActivity(), list);
        rvUsers.setLayoutManager(manager);
        rvUsers.setAdapter(mAdapter);
    }
}
