package com.kalashnyk.denys.gmapsapp.di.component;

import com.kalashnyk.denys.gmapsapp.di.module.DaoModule;
import com.kalashnyk.denys.gmapsapp.repository.database.dao.PinDao;

import dagger.Component;

@Component(modules = {DaoModule.class})
public interface DaoComponent {
    PinDao getUserDao();
}
