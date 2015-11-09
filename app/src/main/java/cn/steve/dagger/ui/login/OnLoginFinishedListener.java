
package cn.steve.dagger.ui.login;

public interface OnLoginFinishedListener {

    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess();
}
