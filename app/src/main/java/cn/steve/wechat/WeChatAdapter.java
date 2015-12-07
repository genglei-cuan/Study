package cn.steve.wechat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/7.
 */
public class WeChatAdapter extends BaseAdapter {

    public static final int TYPE_ME = 0;
    public static final int TYPE_OTHER = 1;
    private ArrayList<WeChatModel> messages = null;
    private Context context;

    public WeChatAdapter(Context context, ArrayList<WeChatModel> messages) {
        this.context = context;
        this.messages = messages;
    }


    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public WeChatModel getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
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
                    view = LayoutInflater.from(context).inflate(R.layout.wechat_me, null);
                    viewHolder.textView = (TextView) view.findViewById(R.id.chatListTextMe);
                    view.setTag(viewHolder);
                } else {
                    view = convertView;
                    viewHolder = (ViewHolder) view.getTag();
                }
                break;
            case TYPE_OTHER:
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    view = LayoutInflater.from(context).inflate(R.layout.wechat_other, null);
                    viewHolder.textView = (TextView) view.findViewById(R.id.chatListTextOther);
                    view.setTag(viewHolder);
                } else {
                    view = convertView;
                    viewHolder = (ViewHolder) view.getTag();
                }
                break;
        }
        viewHolder.textView.setText(getItem(position).getTextContent());
        return view;
    }

    static class ViewHolder {

        TextView textView;
    }


}
