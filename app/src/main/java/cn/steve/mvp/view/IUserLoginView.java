package cn.steve.mvp.view;

import cn.steve.mvp.bean.User;

/**
 * Created by yantinggeng on 2015/12/29.
 */
public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
