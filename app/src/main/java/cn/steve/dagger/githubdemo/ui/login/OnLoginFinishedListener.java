
package cn.steve.dagger.githubdemo.ui.login;

public interface OnLoginFinishedListener {

    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess();
}
