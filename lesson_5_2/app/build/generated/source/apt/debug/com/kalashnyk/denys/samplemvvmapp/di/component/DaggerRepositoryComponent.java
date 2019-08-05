// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.samplemvvmapp.di.component;

import com.kalashnyk.denys.samplemvvmapp.di.module.RepositoryModule;
import com.kalashnyk.denys.samplemvvmapp.di.module.RepositoryModule_ProvidesRepositoryFactory;
import com.kalashnyk.denys.samplemvvmapp.repository.AppRepository;
import com.kalashnyk.denys.samplemvvmapp.repository.database.dao.UserDao;
import com.kalashnyk.denys.samplemvvmapp.repository.server.ServerCommunicator;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerRepositoryComponent implements RepositoryComponent {
  private com_kalashnyk_denys_samplemvvmapp_di_component_ApiComponent_getServerCommunicator
      getServerCommunicatorProvider;

  private com_kalashnyk_denys_samplemvvmapp_di_component_DaoComponent_getUserDao getUserDaoProvider;

  private Provider<AppRepository> providesRepositoryProvider;

  private DaggerRepositoryComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.getServerCommunicatorProvider =
        new com_kalashnyk_denys_samplemvvmapp_di_component_ApiComponent_getServerCommunicator(
            builder.apiComponent);
    this.getUserDaoProvider =
        new com_kalashnyk_denys_samplemvvmapp_di_component_DaoComponent_getUserDao(
            builder.daoComponent);
    this.providesRepositoryProvider =
        DoubleCheck.provider(
            RepositoryModule_ProvidesRepositoryFactory.create(
                builder.repositoryModule, getServerCommunicatorProvider, getUserDaoProvider));
  }

  @Override
  public AppRepository getRepository() {
    return providesRepositoryProvider.get();
  }

  public static final class Builder {
    private RepositoryModule repositoryModule;

    private ApiComponent apiComponent;

    private DaoComponent daoComponent;

    private Builder() {}

    public RepositoryComponent build() {
      if (repositoryModule == null) {
        this.repositoryModule = new RepositoryModule();
      }
      if (apiComponent == null) {
        throw new IllegalStateException(ApiComponent.class.getCanonicalName() + " must be set");
      }
      if (daoComponent == null) {
        throw new IllegalStateException(DaoComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerRepositoryComponent(this);
    }

    public Builder repositoryModule(RepositoryModule repositoryModule) {
      this.repositoryModule = Preconditions.checkNotNull(repositoryModule);
      return this;
    }

    public Builder apiComponent(ApiComponent apiComponent) {
      this.apiComponent = Preconditions.checkNotNull(apiComponent);
      return this;
    }

    public Builder daoComponent(DaoComponent daoComponent) {
      this.daoComponent = Preconditions.checkNotNull(daoComponent);
      return this;
    }
  }

  private static
  class com_kalashnyk_denys_samplemvvmapp_di_component_ApiComponent_getServerCommunicator
      implements Provider<ServerCommunicator> {
    private final ApiComponent apiComponent;

    com_kalashnyk_denys_samplemvvmapp_di_component_ApiComponent_getServerCommunicator(
        ApiComponent apiComponent) {
      this.apiComponent = apiComponent;
    }

    @Override
    public ServerCommunicator get() {
      return Preconditions.checkNotNull(
          apiComponent.getServerCommunicator(),
          "Cannot return null from a non-@Nullable component method");
    }
  }

  private static class com_kalashnyk_denys_samplemvvmapp_di_component_DaoComponent_getUserDao
      implements Provider<UserDao> {
    private final DaoComponent daoComponent;

    com_kalashnyk_denys_samplemvvmapp_di_component_DaoComponent_getUserDao(
        DaoComponent daoComponent) {
      this.daoComponent = daoComponent;
    }

    @Override
    public UserDao get() {
      return Preconditions.checkNotNull(
          daoComponent.getUserDao(), "Cannot return null from a non-@Nullable component method");
    }
  }
}
