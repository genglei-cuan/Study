
package cn.steve.dagger.githubdemo.ui.login;


import javax.inject.Singleton;

import cn.steve.dagger.githubdemo.AppModule;
import cn.steve.dagger.githubdemo.interactors.LoginInteractor;
import dagger.Module;
import dagger.Provides;

@Module(
    injects = LoginActivity.class,
    addsTo = AppModule.class
)
public class LoginModule {

    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public LoginView provideView() {
        return view;
    }

    @Provides
    @Singleton
    public LoginPresenter providePresenter(LoginView loginView, LoginInteractor loginInteractor) {
        return new LoginPresenterImpl(loginView, loginInteractor);
    }
}
