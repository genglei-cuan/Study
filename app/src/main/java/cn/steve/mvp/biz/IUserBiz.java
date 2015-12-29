package cn.steve.mvp.biz;

/**
 * Created by yantinggeng on 2015/12/29.
 */
public interface IUserBiz {

    void login(String userName, String passWord, OnLoginListener loginListener);

}
