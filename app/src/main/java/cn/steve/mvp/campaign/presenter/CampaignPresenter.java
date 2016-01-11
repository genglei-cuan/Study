package cn.steve.mvp.campaign.presenter;


import java.util.List;

import cn.steve.mvp.campaign.bean.Campaign;
import cn.steve.mvp.campaign.biz.ICampaignBiz;
import cn.steve.mvp.campaign.view.ICampaignView;
import cn.steve.mvp.campaign.view.IView;

/**
 * Created by yantinggeng on 2016/1/11.
 */
public class CampaignPresenter implements IPresenter {

    private ICampaignView campaignView;
    private ICampaignBiz campaignBiz;

    //constructor
    public CampaignPresenter(ICampaignBiz campaignBiz) {
        this.campaignBiz = campaignBiz;
    }

    @Override
    public void setView(IView view) {
        this.campaignView = (ICampaignView) view;
    }

    @Override
    public void start() {
        campaignView.showLoading("start loading");
        //请求数据，更新界面、
        List<Campaign> data = campaignBiz.getData();
        campaignView.setListViewModels(data);
    }

    @Override
    public void stop() {
        campaignView.hideLoading(true);
    }

    @Override
    public void onError(Exception exception) {
    }

    public void handleClick(String id){
        campaignBiz.intent2Detail(id);
    }
}
