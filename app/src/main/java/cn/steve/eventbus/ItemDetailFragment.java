package cn.steve.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

import cn.steve.eventbus.dummy.Item;
import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/9/28.
 */
public class ItemDetailFragment extends Fragment {

    private TextView textViewDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(Item item) {
        if (item != null) {
            textViewDetail.setText(item.content);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_main_textview, container);
        this.textViewDetail = (TextView) view.findViewById(R.id.textViewMain);
        return view;
    }

}
