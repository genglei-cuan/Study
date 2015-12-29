package cn.steve.mvp.biz;

import cn.steve.mvp.bean.User;

/**
 * Created by yantinggeng on 2015/12/29.
 */
public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();
}
