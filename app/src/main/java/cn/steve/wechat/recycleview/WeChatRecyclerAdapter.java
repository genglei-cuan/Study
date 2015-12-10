package cn.steve.wechat.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;
import cn.steve.wechat.WeChatModel;

/**
 * Created by yantinggeng on 2015/12/10.
 */
public class WeChatRecyclerAdapter extends RecyclerView.Adapter<WeChatRecyclerAdapter.ChatViewHolder> {

    public static final int TYPE_ME = 0;
    public static final int TYPE_OTHER = 1;

    private ArrayList<WeChatModel> messages = null;
    private Context context;

    public WeChatRecyclerAdapter(Context context, ArrayList<WeChatModel> messages) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_ME:
                view = LayoutInflater.from(context).inflate(R.layout.wechat_me, parent, false);
                break;
            case TYPE_OTHER:
                view = LayoutInflater.from(context).inflate(R.layout.wechat_other, parent, false);
                break;
        }
        ChatViewHolder chatViewHolder = new ChatViewHolder(view,viewType);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.textView.setText(messages.get(position).getTextContent());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getType();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ChatViewHolder(View itemView,int itemViewType) {
            super(itemView);
            switch (itemViewType) {
                case TYPE_ME:
                    textView = (TextView) itemView.findViewById(R.id.chatListTextMe);
                    break;
                case TYPE_OTHER:
                    textView = (TextView) itemView.findViewById(R.id.chatListTextOther);
                    break;
            }
        }
    }

}
