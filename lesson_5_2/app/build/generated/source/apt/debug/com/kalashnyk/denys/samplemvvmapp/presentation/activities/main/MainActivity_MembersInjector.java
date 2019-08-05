// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.samplemvvmapp.presentation.activities.main;

import com.kalashnyk.denys.samplemvvmapp.domain.AllUsersViewModel;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<AllUsersViewModel> mViewModelProvider;

  public MainActivity_MembersInjector(Provider<AllUsersViewModel> mViewModelProvider) {
    this.mViewModelProvider = mViewModelProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<AllUsersViewModel> mViewModelProvider) {
    return new MainActivity_MembersInjector(mViewModelProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectMViewModel(instance, mViewModelProvider.get());
  }

  public static void injectMViewModel(MainActivity instance, AllUsersViewModel mViewModel) {
    instance.mViewModel = mViewModel;
  }
}
