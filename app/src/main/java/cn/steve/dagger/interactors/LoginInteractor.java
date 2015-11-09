package cn.steve.dagger.interactors;


import cn.steve.dagger.ui.login.OnLoginFinishedListener;

public interface LoginInteractor {

    public void login(String username, String password, OnLoginFinishedListener listener);
}
