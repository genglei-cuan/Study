package cn.steve.listview.customitemheight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/11/5.
 */
public class CustomListViewAdapter extends BaseAdapter {

    private ArrayList<String> datas;
    private Context mContext;

    public CustomListViewAdapter(Context context, ArrayList<String> datas) {
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View root = null;
        if (view == null) {
            //这种方式下，自定义的item高度将会被忽略
            root =
                LayoutInflater.from(mContext).inflate(R.layout.activity_customlistview_item, null);
            //采用这种方式的话，以为第三个参数是false，viewGroup只是提供一个标准布局参数的作用，以起到布局文件中的宽高属性都能起作用
            //还有一种方案就是在布局文件中的最外围提供一个父布局就可以了。
//            root =LayoutInflater.from(mContext).inflate(R.layout.activity_customlistview_item, viewGroup,false);
        } else {
            root = view;
        }
        TextView text = (TextView) root.findViewById(R.id.textView);
        text.setText(getItem(i).toString());
        return root;
    }

}
