package cn.steve.mvp.campaign.presenter;


import java.util.List;

import cn.steve.mvp.campaign.bean.Campaign;
import cn.steve.mvp.campaign.biz.ICampaignBiz;
import cn.steve.mvp.campaign.view.ICampaignView;
import cn.steve.mvp.campaign.view.IView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yantinggeng on 2016/1/11.
 */
public class CampaignPresenter implements IPresenter {

    private ICampaignView campaignView;
    private ICampaignBiz campaignBiz;
    private Subscription subscription;


    //constructor
    public CampaignPresenter(ICampaignBiz campaignBiz) {
        this.campaignBiz = campaignBiz;
    }

    @Override
    public void attachView(IView view) {
        this.campaignView = (ICampaignView) view;
    }

    @Override
    public void detachView() {
        this.campaignView = null;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void start() {
        campaignView.showLoading("start loading");
    }


    @Override
    public void stop() {
        campaignView.hideLoading(true);
    }

    @Override
    public void onError(Exception exception) {
    }

    public void handleClick(String id) {
        campaignBiz.intent2Detail(id);
    }

    public void loadCampaigns() {
        //请求数据，更新界面、
        subscription = Observable.create(new Observable.OnSubscribe<List<Campaign>>() {
            @Override
            public void call(Subscriber<? super List<Campaign>> subscriber) {
                List<Campaign> data = campaignBiz.getData();
                subscriber.onNext(data);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
            new Action1<List<Campaign>>() {
                @Override
                public void call(List<Campaign> campaigns) {
                    campaignView.setListViewModels(campaigns);
                }
            });
    }
}
