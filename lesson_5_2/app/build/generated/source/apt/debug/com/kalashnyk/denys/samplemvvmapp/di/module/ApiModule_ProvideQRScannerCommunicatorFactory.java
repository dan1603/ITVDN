// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.samplemvvmapp.di.module;

import com.kalashnyk.denys.samplemvvmapp.repository.server.ApiService;
import com.kalashnyk.denys.samplemvvmapp.repository.server.ServerCommunicator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApiModule_ProvideQRScannerCommunicatorFactory
    implements Factory<ServerCommunicator> {
  private final ApiModule module;

  private final Provider<ApiService> apiServiceProvider;

  public ApiModule_ProvideQRScannerCommunicatorFactory(
      ApiModule module, Provider<ApiService> apiServiceProvider) {
    this.module = module;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public ServerCommunicator get() {
    return Preconditions.checkNotNull(
        module.provideQRScannerCommunicator(apiServiceProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static ApiModule_ProvideQRScannerCommunicatorFactory create(
      ApiModule module, Provider<ApiService> apiServiceProvider) {
    return new ApiModule_ProvideQRScannerCommunicatorFactory(module, apiServiceProvider);
  }

  public static ServerCommunicator proxyProvideQRScannerCommunicator(
      ApiModule instance, ApiService apiService) {
    return Preconditions.checkNotNull(
        instance.provideQRScannerCommunicator(apiService),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}