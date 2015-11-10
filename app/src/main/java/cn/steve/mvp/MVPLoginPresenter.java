package cn.steve.mvp;

/**
 * 业务操作类，实现对登录的验证
 */
public interface MVPLoginPresenter {

    void validateCredentials(String username, String password);
}
