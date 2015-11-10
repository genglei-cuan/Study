package cn.steve.mvp;

//view部分的接口，负责对UI的操作,再被presenter依赖注入，这样就可以实现对view部分的操作
public interface MVPLoginView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
