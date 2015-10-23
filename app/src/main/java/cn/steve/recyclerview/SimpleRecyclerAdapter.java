package cn.steve.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.steve.study.R;


/**
 * Created by Steve on 2015/8/25.
 */
public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder>
    implements View.OnClickListener {

    public String[] datas = null;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public SimpleRecyclerAdapter(String[] datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_item,
                                                                        viewGroup, false);
        view.findViewById(R.id.text).setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText(datas[position]);
        viewHolder.mTextView.setTag(datas[position]);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.length;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        this.mOnItemClickListener.onItemClick(v, v.getTag().toString());
    }

    public interface OnRecyclerViewItemClickListener {

        void onItemClick(View view, String data);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
        }
    }
}