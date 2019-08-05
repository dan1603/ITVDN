package com.kalashnyk.denys.samplemvvmapp.di.component;

import com.kalashnyk.denys.samplemvvmapp.di.module.RepositoryModule;
import com.kalashnyk.denys.samplemvvmapp.di.scope.RepositoryScope;
import com.kalashnyk.denys.samplemvvmapp.repository.AppRepository;

import dagger.Component;

@RepositoryScope
@Component(modules = {RepositoryModule.class}, dependencies = {ApiComponent.class, DaoComponent.class})
public interface RepositoryComponent {
    AppRepository getRepository();
}
