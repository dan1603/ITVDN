// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.simplemvpapp.di.module;

import com.kalashnyk.denys.simplemvpapp.data.network.ApiService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class ApiModule_ProvideApiServiceFactory implements Factory<ApiService> {
  private final ApiModule module;

  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideApiServiceFactory(ApiModule module, Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ApiService get() {
    return Preconditions.checkNotNull(
        module.provideApiService(retrofitProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static ApiModule_ProvideApiServiceFactory create(
      ApiModule module, Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideApiServiceFactory(module, retrofitProvider);
  }

  public static ApiService proxyProvideApiService(ApiModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNull(
        instance.provideApiService(retrofit),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
