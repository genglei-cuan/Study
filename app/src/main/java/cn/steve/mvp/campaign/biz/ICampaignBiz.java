package cn.steve.mvp.campaign.biz;


import java.util.List;

import cn.steve.mvp.campaign.bean.Campaign;

/**
 * Created by yantinggeng on 2016/1/11.
 */
public interface ICampaignBiz {

    void intent2Detail(String id);

    List<Campaign> getData();

}
