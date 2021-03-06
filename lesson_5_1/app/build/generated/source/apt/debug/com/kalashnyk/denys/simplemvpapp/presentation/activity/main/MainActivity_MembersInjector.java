// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.simplemvpapp.presentation.activity.main;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<MainContract.Presenter> mPresenterProvider;

  public MainActivity_MembersInjector(Provider<MainContract.Presenter> mPresenterProvider) {
    this.mPresenterProvider = mPresenterProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<MainContract.Presenter> mPresenterProvider) {
    return new MainActivity_MembersInjector(mPresenterProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectMPresenter(instance, mPresenterProvider.get());
  }

  public static void injectMPresenter(MainActivity instance, MainContract.Presenter mPresenter) {
    instance.mPresenter = mPresenter;
  }
}
