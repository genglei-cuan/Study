package cn.steve.mvp.campaign.view;


import java.util.List;

import cn.steve.mvp.campaign.bean.Campaign;

/**
 * Created by yantinggeng on 2016/1/11.
 */
public interface ICampaignView extends IView{

    void setListViewModels(List<Campaign> data);

}
