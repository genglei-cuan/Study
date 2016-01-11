package cn.steve.mvp.simple.presenter;

import android.os.Handler;

import cn.steve.mvp.simple.bean.User;
import cn.steve.mvp.simple.biz.IUserBiz;
import cn.steve.mvp.simple.biz.OnLoginListener;
import cn.steve.mvp.simple.biz.UserBiz;
import cn.steve.mvp.simple.view.IUserLoginView;

/**
 * Created by yantinggeng on 2015/12/29.
 */
public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(),
                      new OnLoginListener() {
                          @Override
                          public void loginSuccess(final User user) {
                              mHandler.post(new Runnable() {
                                  @Override
                                  public void run() {

                                      userLoginView.toMainActivity(user);
                                      userLoginView.hideLoading();

                                  }
                              });
                          }

                          @Override
                          public void loginFailed() {
                              mHandler.post(new Runnable() {
                                  @Override
                                  public void run() {
                                      userLoginView.showFailedError();
                                      userLoginView.hideLoading();
                                  }
                              });

                          }
                      });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }


}
