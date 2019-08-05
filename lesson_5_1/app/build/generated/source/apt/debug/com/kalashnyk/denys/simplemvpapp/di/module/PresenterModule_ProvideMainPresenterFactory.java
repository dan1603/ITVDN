// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.simplemvpapp.di.module;

import com.kalashnyk.denys.simplemvpapp.domain.UserInteractor;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.main.MainContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class PresenterModule_ProvideMainPresenterFactory
    implements Factory<MainContract.Presenter> {
  private final PresenterModule module;

  private final Provider<UserInteractor> userInteractorProvider;

  public PresenterModule_ProvideMainPresenterFactory(
      PresenterModule module, Provider<UserInteractor> userInteractorProvider) {
    this.module = module;
    this.userInteractorProvider = userInteractorProvider;
  }

  @Override
  public MainContract.Presenter get() {
    return Preconditions.checkNotNull(
        module.provideMainPresenter(userInteractorProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static PresenterModule_ProvideMainPresenterFactory create(
      PresenterModule module, Provider<UserInteractor> userInteractorProvider) {
    return new PresenterModule_ProvideMainPresenterFactory(module, userInteractorProvider);
  }

  public static MainContract.Presenter proxyProvideMainPresenter(
      PresenterModule instance, UserInteractor userInteractor) {
    return Preconditions.checkNotNull(
        instance.provideMainPresenter(userInteractor),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}