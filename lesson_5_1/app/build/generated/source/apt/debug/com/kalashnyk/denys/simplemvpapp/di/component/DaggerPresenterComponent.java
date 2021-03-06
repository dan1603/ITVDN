// Generated by Dagger (https://google.github.io/dagger).
package com.kalashnyk.denys.simplemvpapp.di.component;

import com.kalashnyk.denys.simplemvpapp.di.module.PresenterModule;
import com.kalashnyk.denys.simplemvpapp.di.module.PresenterModule_ProvideAuthPresenterFactory;
import com.kalashnyk.denys.simplemvpapp.di.module.PresenterModule_ProvideMainPresenterFactory;
import com.kalashnyk.denys.simplemvpapp.domain.UserInteractor;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.detail.DetailActivity;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.detail.DetailActivity_MembersInjector;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.detail.DetailContract;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.main.MainActivity;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.main.MainActivity_MembersInjector;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.main.MainContract;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerPresenterComponent implements PresenterComponent {
  private com_kalashnyk_denys_simplemvpapp_di_component_InteractorComponent_getUserInteractor
      getUserInteractorProvider;

  private Provider<DetailContract.Presenter> provideAuthPresenterProvider;

  private Provider<MainContract.Presenter> provideMainPresenterProvider;

  private DaggerPresenterComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.getUserInteractorProvider =
        new com_kalashnyk_denys_simplemvpapp_di_component_InteractorComponent_getUserInteractor(
            builder.interactorComponent);
    this.provideAuthPresenterProvider =
        DoubleCheck.provider(
            PresenterModule_ProvideAuthPresenterFactory.create(
                builder.presenterModule, getUserInteractorProvider));
    this.provideMainPresenterProvider =
        DoubleCheck.provider(
            PresenterModule_ProvideMainPresenterFactory.create(
                builder.presenterModule, getUserInteractorProvider));
  }

  @Override
  public void inject(DetailActivity activity) {
    injectDetailActivity(activity);
  }

  @Override
  public void inject(MainActivity activity) {
    injectMainActivity(activity);
  }

  private DetailActivity injectDetailActivity(DetailActivity instance) {
    DetailActivity_MembersInjector.injectMPresenter(instance, provideAuthPresenterProvider.get());
    return instance;
  }

  private MainActivity injectMainActivity(MainActivity instance) {
    MainActivity_MembersInjector.injectMPresenter(instance, provideMainPresenterProvider.get());
    return instance;
  }

  public static final class Builder {
    private PresenterModule presenterModule;

    private InteractorComponent interactorComponent;

    private Builder() {}

    public PresenterComponent build() {
      if (presenterModule == null) {
        this.presenterModule = new PresenterModule();
      }
      if (interactorComponent == null) {
        throw new IllegalStateException(
            InteractorComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerPresenterComponent(this);
    }

    public Builder presenterModule(PresenterModule presenterModule) {
      this.presenterModule = Preconditions.checkNotNull(presenterModule);
      return this;
    }

    public Builder interactorComponent(InteractorComponent interactorComponent) {
      this.interactorComponent = Preconditions.checkNotNull(interactorComponent);
      return this;
    }
  }

  private static
  class com_kalashnyk_denys_simplemvpapp_di_component_InteractorComponent_getUserInteractor
      implements Provider<UserInteractor> {
    private final InteractorComponent interactorComponent;

    com_kalashnyk_denys_simplemvpapp_di_component_InteractorComponent_getUserInteractor(
        InteractorComponent interactorComponent) {
      this.interactorComponent = interactorComponent;
    }

    @Override
    public UserInteractor get() {
      return Preconditions.checkNotNull(
          interactorComponent.getUserInteractor(),
          "Cannot return null from a non-@Nullable component method");
    }
  }
}
