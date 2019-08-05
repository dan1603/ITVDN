package com.kalashnyk.denys.retrofitexample.di.component;

import com.kalashnyk.denys.retrofitexample.di.module.UserApiModule;
import com.kalashnyk.denys.retrofitexample.di.scope.AppScope;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.AddUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.AllUsersFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.DeleteUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.EditUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.FileFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.SingleUserFragment;

import dagger.Component;

@AppScope
@Component(modules = {UserApiModule.class})
public interface UserApiComponent {
    void inject(AddUserFragment fragment);
    void inject(AllUsersFragment fragment);
    void inject(DeleteUserFragment fragment);
    void inject(EditUserFragment fragment);
    void inject(FileFragment fragment);
    void inject(SingleUserFragment fragment);
}
