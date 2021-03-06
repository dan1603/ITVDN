// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.retrofitexample.di.component;

import com.kalashnyk.denys.retrofitexample.di.module.UserApiModule;
import com.kalashnyk.denys.retrofitexample.di.module.UserApiModule_ProvideRetrofitBuilderFactory;
import com.kalashnyk.denys.retrofitexample.di.module.UserApiModule_ProvideRetrofitFactory;
import com.kalashnyk.denys.retrofitexample.di.module.UserApiModule_ProvideUserApiServiceFactory;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.AddUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.AddUserFragment_MembersInjector;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.AllUsersFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.AllUsersFragment_MembersInjector;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.DeleteUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.DeleteUserFragment_MembersInjector;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.EditUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.EditUserFragment_MembersInjector;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.FileFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.FileFragment_MembersInjector;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.SingleUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.SingleUserFragment_MembersInjector;
import com.kalashnyk.denys.retrofitexample.server.UserAPI;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class DaggerUserApiComponent implements UserApiComponent {
  private Provider<Retrofit.Builder> provideRetrofitBuilderProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<UserAPI> provideUserApiServiceProvider;

  private DaggerUserApiComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static UserApiComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideRetrofitBuilderProvider =
        DoubleCheck.provider(
            UserApiModule_ProvideRetrofitBuilderFactory.create(builder.userApiModule));
    this.provideRetrofitProvider =
        DoubleCheck.provider(
            UserApiModule_ProvideRetrofitFactory.create(
                builder.userApiModule, provideRetrofitBuilderProvider));
    this.provideUserApiServiceProvider =
        DoubleCheck.provider(
            UserApiModule_ProvideUserApiServiceFactory.create(
                builder.userApiModule, provideRetrofitProvider));
  }

  @Override
  public void inject(AddUserFragment fragment) {
    injectAddUserFragment(fragment);
  }

  @Override
  public void inject(AllUsersFragment fragment) {
    injectAllUsersFragment(fragment);
  }

  @Override
  public void inject(DeleteUserFragment fragment) {
    injectDeleteUserFragment(fragment);
  }

  @Override
  public void inject(EditUserFragment fragment) {
    injectEditUserFragment(fragment);
  }

  @Override
  public void inject(FileFragment fragment) {
    injectFileFragment(fragment);
  }

  @Override
  public void inject(SingleUserFragment fragment) {
    injectSingleUserFragment(fragment);
  }

  private AddUserFragment injectAddUserFragment(AddUserFragment instance) {
    AddUserFragment_MembersInjector.injectMApi(instance, provideUserApiServiceProvider.get());
    return instance;
  }

  private AllUsersFragment injectAllUsersFragment(AllUsersFragment instance) {
    AllUsersFragment_MembersInjector.injectMApi(instance, provideUserApiServiceProvider.get());
    return instance;
  }

  private DeleteUserFragment injectDeleteUserFragment(DeleteUserFragment instance) {
    DeleteUserFragment_MembersInjector.injectMApi(instance, provideUserApiServiceProvider.get());
    return instance;
  }

  private EditUserFragment injectEditUserFragment(EditUserFragment instance) {
    EditUserFragment_MembersInjector.injectMApi(instance, provideUserApiServiceProvider.get());
    return instance;
  }

  private FileFragment injectFileFragment(FileFragment instance) {
    FileFragment_MembersInjector.injectMApi(instance, provideUserApiServiceProvider.get());
    return instance;
  }

  private SingleUserFragment injectSingleUserFragment(SingleUserFragment instance) {
    SingleUserFragment_MembersInjector.injectMApi(instance, provideUserApiServiceProvider.get());
    return instance;
  }

  public static final class Builder {
    private UserApiModule userApiModule;

    private Builder() {}

    public UserApiComponent build() {
      if (userApiModule == null) {
        this.userApiModule = new UserApiModule();
      }
      return new DaggerUserApiComponent(this);
    }

    public Builder userApiModule(UserApiModule userApiModule) {
      this.userApiModule = Preconditions.checkNotNull(userApiModule);
      return this;
    }
  }
}
