// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.retrofitexample.presentation.fragments;

import com.kalashnyk.denys.retrofitexample.server.UserAPI;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AllUsersFragment_MembersInjector implements MembersInjector<AllUsersFragment> {
  private final Provider<UserAPI> mApiProvider;

  public AllUsersFragment_MembersInjector(Provider<UserAPI> mApiProvider) {
    this.mApiProvider = mApiProvider;
  }

  public static MembersInjector<AllUsersFragment> create(Provider<UserAPI> mApiProvider) {
    return new AllUsersFragment_MembersInjector(mApiProvider);
  }

  @Override
  public void injectMembers(AllUsersFragment instance) {
    injectMApi(instance, mApiProvider.get());
  }

  public static void injectMApi(AllUsersFragment instance, UserAPI mApi) {
    instance.mApi = mApi;
  }
}
