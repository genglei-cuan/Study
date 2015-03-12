package cn.steve.listview.cache;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.steve.study.R;

public class LazyAdapter extends BaseAdapter {

  private Activity activity;
  private String[] data;
  private static LayoutInflater inflater = null;
  public ImageLoader imageLoader;

  public LazyAdapter(Activity a, String[] d) {
    activity = a;
    data = d;
    inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    imageLoader = new ImageLoader(activity.getApplicationContext());
  }

  public int getCount() {
    return data.length;
  }

  public Object getItem(int position) {
    return position;
  }

  public long getItemId(int position) {
    return position;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    View vi = convertView;
    if (convertView == null) vi = inflater.inflate(R.layout.activitylazylist_item, null);
    // TextView text=(TextView)vi.findViewById(R.id.text);;
    // ImageView image=(ImageView)vi.findViewById(R.id.image);
    // ImageView image2=(ImageView)vi.findViewById(R.id.image2);
    // text.setText("item "+position);
    ImageView image = BaseViewHolder.get(vi, R.id.image);
    ImageView image2 = BaseViewHolder.get(vi, R.id.image2);
    imageLoader.DisplayImage(data[position], image);
    imageLoader.DisplayImage(data[position], image2);
    return vi;
  }
}
