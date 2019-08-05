package com.kalashnyk.denys.simplemvpapp.di.component;

import com.kalashnyk.denys.simplemvpapp.data.database.UserDao;
import com.kalashnyk.denys.simplemvpapp.di.module.DAOModule;

import dagger.Component;

@Component(modules = {DAOModule.class})
public interface DAOComponent {
    UserDao getUserDAO();
}
