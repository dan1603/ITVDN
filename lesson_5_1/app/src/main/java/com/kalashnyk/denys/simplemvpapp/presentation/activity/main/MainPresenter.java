package com.kalashnyk.denys.simplemvpapp.presentation.activity.main;

import android.util.Log;

import com.kalashnyk.denys.simplemvpapp.domain.UserInteractor;
import com.kalashnyk.denys.simplemvpapp.presentation.base.BasePresenter;

import io.reactivex.disposables.Disposable;

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    private UserInteractor mUserInteractor;

    public MainPresenter(UserInteractor userInteractor) {
        mUserInteractor = userInteractor;
    }

    @Override
    public void getUsers() {
        Disposable disposable = mUserInteractor.getUsers()
                .subscribe(userEntityList -> {
                    getView().showUsers(userEntityList);
                }, throwable ->
                        Log.e("USER", "MainPresenter\ngetUsers\nthrowable\n"
                                + throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }
}