package cn.steve.mvp;

import cn.steve.dagger.githubdemo.ui.login.OnLoginFinishedListener;

/**
 * 行为的操作类
 */
public class MVPLoginPresenterImpl implements MVPLoginPresenter, OnLoginFinishedListener {

    private MVPLoginView loginView;
    //需要被分离的行为
    private MVPLoginInteractor MVPLoginInteractor;

    public MVPLoginPresenterImpl(MVPLoginView loginView) {
        this.loginView = loginView;
        this.MVPLoginInteractor = new MVPLoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        loginView.showProgress();
        MVPLoginInteractor.login(username, password, this);
    }

    @Override
    public void onUsernameError() {
        loginView.setUsernameError();
        loginView.hideProgress();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
        loginView.hideProgress();
    }

    @Override
    public void onSuccess() {
        loginView.hideProgress();
        loginView.navigateToHome();
    }
}
