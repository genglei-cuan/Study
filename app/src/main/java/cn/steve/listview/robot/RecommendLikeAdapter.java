package cn.steve.listview.robot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/22.
 */
public class RecommendLikeAdapter extends BaseAdapter {

    private ArrayList<String> data;
    private Context context;


    public RecommendLikeAdapter(Context context, ArrayList<String> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            v = LayoutInflater.from(context).inflate(R.layout.recommend_itemlayout, null);
            holder.textView = (TextView) v.findViewById(R.id.recommendTextView);
            v.setTag(holder);
        } else {
            v = convertView;
            holder = (ViewHolder) v.getTag();
        }
        holder.textView.setText(getItem(position));
        return v;
    }


    class ViewHolder {

        TextView textView;
    }


}
