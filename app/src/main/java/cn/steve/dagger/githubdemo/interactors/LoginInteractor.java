package cn.steve.dagger.githubdemo.interactors;


import cn.steve.dagger.githubdemo.ui.login.OnLoginFinishedListener;

public interface LoginInteractor {

    public void login(String username, String password, OnLoginFinishedListener listener);
}
