package cn.steve.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/12.
 */
public class GalleryStringAdapter extends BaseAdapter {

    private ArrayList<String> data;
    private Context mContext;

    public GalleryStringAdapter(ArrayList<String> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_main_textview, null);
        } else {
            view = convertView;
        }
        TextView tv = (TextView) view.findViewById(R.id.textViewMain);
        tv.setText(getItem(position));
        return view;
    }
}
