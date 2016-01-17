package cn.steve.eventbus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cn.steve.eventbus.Event.ItemListEvent;
import cn.steve.eventbus.dummy.ContentHelper;
import cn.steve.eventbus.dummy.Item;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class ItemListFragment extends ListFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private AbsListView mListView;

    public ItemListFragment() {
    }

    public static ItemListFragment newInstance(String param1, String param2) {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册事件总线
        //在onCreate里面执行	EventBus.getDefault().register(this);意思是让EventBus扫描当前类，把所有onEvent开头的方法记录下来，如何记录呢？使用Map，Key为方法的参数类型，Value中包含我们的方法。
        //这样在onCreate执行完成以后，我们的onEventMainThread就已经以键值对的方式被存储到EventBus中了。
        //EventBus会根据post中实参的类型，去Map中查找对于的方法，于是找到了我们的onEventMainThread，最终调用反射去执行我们的方法。
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * update the ListView
     *
     * @param event published event
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void updateListView(ItemListEvent event) {
        //利用传递过来的事件中的数据封装成适配器
        ArrayAdapter<Item> itemArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                event.getItems());
        setListAdapter(itemArrayAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //开启线程加载列表
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    //发布事件，在后台线程发的事件
                    EventBus.getDefault().post(new ItemListEvent(ContentHelper.Items));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (null != mListener) {
            mListener.onFragmentInteraction(ContentHelper.Items.get(position).id);
        }
        EventBus.getDefault().post(getListView().getItemAtPosition(position));
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();
        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(String id);
    }

}
