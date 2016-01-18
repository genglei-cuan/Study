package cn.steve.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/1/18.
 */
public class RecommendAdapter extends BaseAdapter {

    private ArrayList<Product> datas;
    private Context context;

    public RecommendAdapter(Context context, ArrayList<Product> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Product getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.recommend_item, null);
            viewHolder.imageView =(ImageView) view.findViewById(R.id.recommendAttractionsImageView);
            viewHolder.attractionName =(TextView) view.findViewById(R.id.recommendAttractionsTextView);
            viewHolder.textViewPrePrice =(TextView) view.findViewById(R.id.recommendPrePriceTextView);
            viewHolder.price =(TextView) view.findViewById(R.id.recommendPriceTextView);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        Product item = getItem(position);
        viewHolder.attractionName.setText(item.getName());
        viewHolder.textViewPrePrice.setText(item.getPrePrice());
        viewHolder.price.setText(item.getPrice());
        return view;
    }


    private static class ViewHolder {

        ImageView imageView;
        TextView textViewPrePrice;
        TextView attractionName;
        TextView price;

    }


}
