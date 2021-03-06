// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.simplemvpapp.di.module;

import com.kalashnyk.denys.simplemvpapp.data.managers.UserManager;
import com.kalashnyk.denys.simplemvpapp.domain.UserInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class InteractorModule_ProvideUserInteractorFactory
    implements Factory<UserInteractor> {
  private final InteractorModule module;

  private final Provider<UserManager> managerProvider;

  public InteractorModule_ProvideUserInteractorFactory(
      InteractorModule module, Provider<UserManager> managerProvider) {
    this.module = module;
    this.managerProvider = managerProvider;
  }

  @Override
  public UserInteractor get() {
    return Preconditions.checkNotNull(
        module.provideUserInteractor(managerProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static InteractorModule_ProvideUserInteractorFactory create(
      InteractorModule module, Provider<UserManager> managerProvider) {
    return new InteractorModule_ProvideUserInteractorFactory(module, managerProvider);
  }

  public static UserInteractor proxyProvideUserInteractor(
      InteractorModule instance, UserManager manager) {
    return Preconditions.checkNotNull(
        instance.provideUserInteractor(manager),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
