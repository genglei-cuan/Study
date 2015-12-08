package cn.steve.wechat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/7.
 */
public class WeChatMainActivity extends Activity {

    ArrayList<WeChatModel> data = new ArrayList<>();
    WeChatAdapter adapter = null;

    @Bind(R.id.weChatImageViewSend)
    ImageView imageViewSend;

    @Bind(R.id.weChatListView)
    ListView listView;

    @Bind(R.id.weChatEditTextMessage)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        ButterKnife.bind(this);
        initData();
    }


    private void initWidget() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO 原版的微信是根据内容，更改图标
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.weChatImageViewSend)
    void sendMes() {
        String str = editText.getText().toString();
        if (TextUtils.isEmpty(str)) {
        } else {
            data.add(ModelCreater.createModel(str, WeChatAdapter.TYPE_ME));
            adapter.notifyDataSetChanged();
            editText.getText().clear();
        }
    }

    private void initData() {
        WeChatModel model;
        for (int i = 0; i < 10; i++) {
            model = new WeChatModel();
            model.setTextContent("I AM " + i);
            model.setType(i % 2);
            data.add(model);
        }
        adapter = new WeChatAdapter(this, data);
        listView.setAdapter(adapter);
    }


}
