package com.kalashnyk.denys.simplemvpapp.presentation.activity.detail;

import android.util.Log;

import com.kalashnyk.denys.simplemvpapp.domain.UserInteractor;
import com.kalashnyk.denys.simplemvpapp.presentation.base.BasePresenter;

import io.reactivex.disposables.Disposable;

public class DetailPresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter {
    private UserInteractor mUserInteractor;
    public DetailPresenter(UserInteractor userInteractor) {
        mUserInteractor = userInteractor;
    }

    @Override
    public void getUser(int id) {
        Disposable disposable = mUserInteractor.getUserById(id)
                .subscribe(userEntity -> {
                    getView().showUser(userEntity);
                }, throwable -> Log.e("USER", "DetailPresenter\ngetUser\nthrowable\n" + throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }
}
