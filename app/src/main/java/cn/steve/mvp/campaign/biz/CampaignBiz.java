package cn.steve.mvp.campaign.biz;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.steve.mvp.campaign.bean.Campaign;

/**
 * Created by yantinggeng on 2016/1/11.
 */
public class CampaignBiz implements ICampaignBiz {

    private Context mContext;

    public CampaignBiz(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void intent2Detail(String id) {
        //TODO implements the click jump
        System.out.println("has click the " + id);
    }

    @Override
    public List<Campaign> getData() {
        //TODO implemets get the data from  source, such as network or cache
        List<Campaign> data = new ArrayList<>();
        Campaign campaign;
        for (int i = 0; i < 10; i++) {
            campaign = new Campaign();
            campaign.setTime("09:00:" + i);
            campaign.setDescription("H" + i);
            campaign.setImageUrl("wwww");
            campaign.setIsRead(false);
            campaign.setTitle("Title" + i);
            data.add(campaign);
        }
        return data;
    }
}
