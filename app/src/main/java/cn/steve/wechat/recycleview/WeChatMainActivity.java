package cn.steve.wechat.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;
import cn.steve.wechat.ModelCreater;
import cn.steve.wechat.WeChatAdapter;
import cn.steve.wechat.WeChatModel;

/**
 * Created by yantinggeng on 2015/12/10.
 */
public class WeChatMainActivity extends Activity {

    @Bind(R.id.weChatListView)
    RecyclerView recyclerView;

    @Bind(R.id.weChatEditTextMessage)
    EditText editText;

    WeChatRecyclerAdapter adapter;

    private ArrayList<WeChatModel> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_recyclerview);
        ButterKnife.bind(this);
        initData();
    }


    private void initData() {
        WeChatModel model;
        for (int i = 0; i < 10; i++) {
            model = new WeChatModel();
            model.setTextContent("I AM " + i);
            model.setType(i % 2);
            data.add(model);
        }
        adapter = new WeChatRecyclerAdapter(this,data);
        //创建默认的线性LayoutManager ;设置成横向的,默认为竖屏的
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.weChatImageViewSend)
    void sendMes() {
        String str = editText.getText().toString();
        if (TextUtils.isEmpty(str)) {
        } else {
            data.add(ModelCreater.createModel(str, WeChatAdapter.TYPE_ME));
            editText.getText().clear();
            data.add(ModelCreater.createModel(str, WeChatAdapter.TYPE_OTHER));
            //通知更改，这样能启用默认的动画
            adapter.notifyItemInserted(adapter.getItemCount() - 2);
            adapter.notifyItemInserted(adapter.getItemCount()-1);
            recyclerView.scrollToPosition(adapter.getItemCount()-1);
        }
    }


}
