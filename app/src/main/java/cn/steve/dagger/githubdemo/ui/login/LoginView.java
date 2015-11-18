
package cn.steve.dagger.githubdemo.ui.login;

public interface LoginView {

    public void showProgress();

    public void hideProgress();

    public void setUsernameError();

    public void setPasswordError();

    public void navigateToHome();
}
