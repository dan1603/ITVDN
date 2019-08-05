package com.kalashnyk.denys.simplemvpapp.presentation.base;

import android.content.Context;

import com.kalashnyk.denys.simplemvpapp.utils.local_helper.LocaleHelperImpl;

import java.lang.ref.WeakReference;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {

    private WeakReference<V> mView;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public V getView() {
        if (mView == null) return null;
        return mView.get();
    }

    @Override
    public void attachWithView(V view) {
        mView = new WeakReference<>(view);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachPresenter() {
        if (mView != null && mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
            mView.clear();
            mView = null;
        }
    }

    protected String getActualLocale(Context context) {
        return new LocaleHelperImpl().getActualLocale(context).toLanguageTag();
    }
}
