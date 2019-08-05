package com.kalashnyk.denys.gmapsapp.domain;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.kalashnyk.denys.App;

abstract class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(Application application) {
        super(application);
    }

    public App getContext() {
        return getApplication();
    }
}
