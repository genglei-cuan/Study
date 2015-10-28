package cn.steve.recyclerview.itemanimator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/27.
 */
public class RecyclerViewItemAnimatorAdapter
    extends RecyclerView.Adapter<RecyclerViewItemAnimatorAdapter.SimpleViewHolder> {

    public static final int LAST_POSITION = -1;
    private final Context mContext;
    private List<String> mData;

    public RecyclerViewItemAnimatorAdapter(Context context, String[] data) {
        mContext = context;
        if (data != null) {
            mData = new ArrayList<String>(Arrays.asList(data));
        } else {
            mData = new ArrayList<String>();
        }
    }

    public void add(String s, int position) {
        position = position == LAST_POSITION ? getItemCount() : position;
        mData.add(position, s);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position == LAST_POSITION && getItemCount() > 0) {
            position = getItemCount() - 1;
        }

        if (position > LAST_POSITION && position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View
            view =
            LayoutInflater.from(mContext).inflate(R.layout.recycleview_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(mData.get(position));
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Position =" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        public final TextView title;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.text);
        }
    }
}