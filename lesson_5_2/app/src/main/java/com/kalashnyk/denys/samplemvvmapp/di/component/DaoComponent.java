package com.kalashnyk.denys.samplemvvmapp.di.component;

import com.kalashnyk.denys.samplemvvmapp.di.module.DaoModule;
import com.kalashnyk.denys.samplemvvmapp.repository.database.dao.UserDao;

import dagger.Component;

@Component(modules = {DaoModule.class})
public interface DaoComponent {
    UserDao getUserDao();
}
