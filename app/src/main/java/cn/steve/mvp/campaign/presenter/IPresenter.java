package cn.steve.mvp.campaign.presenter;


import cn.steve.mvp.campaign.view.IView;

/**
 * Created by yantinggeng on 2016/1/11.
 */
public interface IPresenter {

    void setView(IView view);

    void start();

    void stop();

    void onError(Exception exception);
}
