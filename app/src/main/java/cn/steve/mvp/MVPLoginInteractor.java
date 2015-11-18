package cn.steve.mvp;

import cn.steve.dagger.githubdemo.ui.login.OnLoginFinishedListener;

/**
 * 定义需要交互部分的行为，是从presenter部分分离的
 */
public interface MVPLoginInteractor {

    void login(String username, String password, OnLoginFinishedListener listener);
}
