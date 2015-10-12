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
 * Created by Steve on 2015/10/12.
 */
public class MyGalleryAdapter extends BaseAdapter {

    private ArrayList<MyGalleryModel> data;
    private Context mContext;

    public MyGalleryAdapter(ArrayList<MyGalleryModel> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public MyGalleryModel getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        MyGalleryModel myGalleryModel = getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.gallery_item, null);
        } else {
            view = convertView;
        }
        TextView galleryTextViewWeek = (TextView) view.findViewById(R.id.galleryTextViewWeek);
        TextView galleryTextViewDay = (TextView) view.findViewById(R.id.galleryTextViewDay);
        galleryTextViewDay.setText(myGalleryModel.getDay() + "");
        galleryTextViewWeek.setText(getWeek(myGalleryModel.getWeek()));
        return view;
    }


    private String getWeek(int week) {
        String s = "一";
        switch (week) {
            case 1:
                s = "日";
                break;
            case 2:
                s = "一";
                break;
            case 3:
                s = "二";
                break;
            case 4:
                s = "三";
                break;
            case 5:
                s = "四";
                break;
            case 6:
                s = "五";
                break;
            case 7:
                s = "六";
                break;
        }
        return s;
    }


}
