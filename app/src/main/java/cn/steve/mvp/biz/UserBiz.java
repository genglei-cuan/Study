package cn.steve.mvp.biz;

import cn.steve.mvp.bean.User;

/**
 * Created by yantinggeng on 2015/12/29.
 */
public class UserBiz implements IUserBiz {

    @Override
    public void login(final String username, final String password,
                      final OnLoginListener loginListener) {
        // 模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 模拟登录成功
                if ("steve".equals(username) && "1111".equals(password)) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFailed();
                }
            }
        }.start();
    }
}
