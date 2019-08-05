package com.kalashnyk.denys.gmapsapp.di.component;

import com.kalashnyk.denys.gmapsapp.di.module.RepositoryModule;
import com.kalashnyk.denys.gmapsapp.di.scope.RepositoryScope;
import com.kalashnyk.denys.gmapsapp.repository.AppRepository;

import dagger.Component;

@RepositoryScope
@Component(modules = {RepositoryModule.class}, dependencies = {ApiComponent.class, DaoComponent.class})
public interface RepositoryComponent {
    AppRepository getRepository();
}
