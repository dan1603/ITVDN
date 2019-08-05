package com.kalashnyk.denys.simplemvpapp.presentation.base;

import android.content.Context;

public interface BaseContract {

    interface View {
        Context getContext();
    }

    interface Presenter<V extends BaseContract.View> {
        V getView();

        void attachWithView(V view);
        void detachPresenter();
    }
}
