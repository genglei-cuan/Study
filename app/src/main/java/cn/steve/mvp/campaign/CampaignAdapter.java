package cn.steve.mvp.campaign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.steve.mvp.campaign.bean.Campaign;
import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/1/11.
 */
public class CampaignAdapter extends BaseAdapter {

    private List<Campaign> data;
    private Context mContext;

    public CampaignAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<Campaign> getData() {
        return data;
    }

    public void setData(List<Campaign> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Campaign getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            root = LayoutInflater.from(mContext).inflate(R.layout.listitem_campaign, parent, false);
            viewHolder.time = (TextView) root.findViewById(R.id.campaignItemTimeTextView);
            viewHolder.imageView = (ImageView) root.findViewById(R.id.campaignItemImageView);
            viewHolder.title = (TextView) root.findViewById(R.id.campaignItemTitleTextView);
            viewHolder.desc = (TextView) root.findViewById(R.id.campaignItemDescTextView);
            viewHolder.redCircle = (ImageView) root.findViewById(R.id.redCircleImageView);
            root.setTag(viewHolder);
        } else {
            root = convertView;
            viewHolder = (ViewHolder) root.getTag();
        }
        Campaign campaign = data.get(position);
        viewHolder.time.setText(campaign.getTime());
        viewHolder.title.setText(campaign.getTitle());
        viewHolder.desc.setText(campaign.getDescription());
        viewHolder.time.setText(campaign.getTime());
        viewHolder.redCircle.setVisibility(campaign.isRead() ? View.INVISIBLE : View.VISIBLE);
        return root;
    }

    static class ViewHolder {

        TextView time;
        ImageView imageView;
        TextView desc;
        TextView title;
        ImageView redCircle;
    }


}
