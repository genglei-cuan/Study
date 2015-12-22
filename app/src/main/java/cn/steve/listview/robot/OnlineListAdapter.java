package cn.steve.listview.robot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/22.
 */
public class OnlineListAdapter extends BaseAdapter {

    public static final int TYPE_ME = 0;
    public static final int TYPE_OTHER = 1;
    public static final int TYPE_TIME = 2;

    private ArrayList<OnlineModel> messages = null;
    private Context context;

    public OnlineListAdapter(Context context, ArrayList<OnlineModel> messages) {
        this.context = context;
        this.messages = messages;
    }


    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public OnlineModel getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder viewHolder = null;
        View view = null;
        switch (type) {
            case TYPE_ME:
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    view = LayoutInflater.from(context).inflate(R.layout.online_me, null);
                    viewHolder.textView = (TextView) view.findViewById(R.id.chatListTextMe);
                    view.setTag(viewHolder);
                } else {
                    view = convertView;
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.textView.setText(getItem(position).getTextContent());
                break;
            case TYPE_OTHER:
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    view = LayoutInflater.from(context).inflate(R.layout.online_other, parent,false);
                    viewHolder.textView = (TextView) view.findViewById(R.id.chatListTextOther);
                    viewHolder.recommendLinearlayout= (LinearLayout) view.findViewById(R.id.recommendLinearLayout);
                    viewHolder.recommendListView = (ListView) view.findViewById(R.id.recommendListView);
                    view.setTag(viewHolder);
                } else {
                    view = convertView;
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.textView.setText(getItem(position).getTextContent());
                ArrayList<String> data=new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    data.add("猜你喜欢" + i);
                }
                RecommendLikeAdapter recommendLikeAdapter = new RecommendLikeAdapter(context, data);
                viewHolder.recommendListView.setAdapter(recommendLikeAdapter);
                break;
            case TYPE_TIME:
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    view = LayoutInflater.from(context).inflate(R.layout.wechat_time, null);
                    viewHolder.textView = (TextView) view.findViewById(R.id.chatTime);
                    view.setTag(viewHolder);
                } else {
                    view = convertView;
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.textView.setText(getItem(position).getTextContent());
                break;
        }
        return view;
    }

    static class ViewHolder {

        LinearLayout recommendLinearlayout;
        ListView recommendListView;
        TextView textView;

    }


}
