package cn.steve.mvp.campaign;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import cn.steve.mvp.campaign.bean.Campaign;
import cn.steve.mvp.campaign.biz.CampaignBiz;
import cn.steve.mvp.campaign.presenter.CampaignPresenter;
import cn.steve.mvp.campaign.view.ICampaignView;
import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/1/11.
 */
public class CampaignActivity extends Activity implements ICampaignView {

    private CampaignPresenter campaignPresenter;
    private CampaignBiz campaignBiz;
    private ListView campaignListView;
    private CampaignAdapter campaignAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);
        this.campaignListView = (ListView) findViewById(R.id.campaignListView);
        campaignAdapter = new CampaignAdapter(this);
        campaignBiz = new CampaignBiz(this);
        campaignPresenter = new CampaignPresenter(campaignBiz);
        campaignPresenter.setView(this);
        campaignPresenter.start();
    }

    @Override
    public void setListViewModels(List<Campaign> data) {
        if (campaignAdapter.getData() != null) {
            campaignAdapter.getData().clear();
        }
        campaignAdapter.setData(data);
        if (this.campaignListView.getAdapter()==null){
            this.campaignListView.setAdapter(campaignAdapter);
        }
        campaignAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading(String message) {
    }

    @Override
    public void hideLoading(boolean success) {

    }
}
