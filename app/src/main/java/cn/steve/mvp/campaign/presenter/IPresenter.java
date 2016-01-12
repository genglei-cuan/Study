package cn.steve.mvp.campaign.presenter;


import cn.steve.mvp.campaign.view.IView;

/**
 * Created by yantinggeng on 2016/1/11.
 */
public interface IPresenter {

    // attach the view ,such activity
    void attachView(IView view);

    //detach the view ,in some case the activity is destoryed something should done
    void detachView();

    //such as the loading dialog
    void start();

    // such as the dialog should dismiss
    void stop();

    // when have some error ,we should have a tip
    void onError(Exception exception);
}
