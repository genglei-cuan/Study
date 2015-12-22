package cn.steve.listview.robot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/22.
 */
public class OnlineActivity extends AppCompatActivity {

    private ListView onlineServiceListView;
    private ImageView onlineServiceImageViewText, onlineServiceImageViewSound,
        onlineServiceImageViewSend;
    private EditText onlineServiceEditTextMessage;
    private TextView onlineServiceTextViewSound;
    private ClickListener mClickListener;
    private LongClickListener mLongClickListener;
    private OnlineListAdapter adapter = null;
    private ArrayList<OnlineModel> messages = new ArrayList<>();
    private LinearLayout onlineServiceSoundChat, onlineServiceTextChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_main);
        mClickListener = new ClickListener();
        mLongClickListener = new LongClickListener();
        initView();
        initData();
        onlineServiceListView.setAdapter(adapter);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        onlineServiceListView = (ListView) findViewById(R.id.onlineServiceListView);
        onlineServiceImageViewText = (ImageView) findViewById(R.id.onlineServiceImageViewText);
        onlineServiceImageViewText.setOnClickListener(mClickListener);
        onlineServiceImageViewSound = (ImageView) findViewById(R.id.onlineServiceImageViewSound);
        onlineServiceImageViewSound.setOnClickListener(mClickListener);
        onlineServiceEditTextMessage = (EditText) findViewById(R.id.onlineServiceEditTextMessage);
        onlineServiceTextViewSound = (TextView) findViewById(R.id.onlineServiceTextViewSound);
        onlineServiceTextViewSound.setOnLongClickListener(mLongClickListener);
        onlineServiceSoundChat = (LinearLayout) findViewById(R.id.onlineServiceSoundChat);
        onlineServiceTextChat = (LinearLayout) findViewById(R.id.onlineServiceTextChat);
        onlineServiceImageViewSend = (ImageView) findViewById(R.id.onlineServiceImageViewSend);
        onlineServiceImageViewSend.setOnClickListener(mClickListener);
    }

    /**
     * 初始化聊天数据
     */
    private void initData() {
        messages.add(ModelCreater.createModel("2015-12-22 14:18", 2));
        for (int i = 0; i < 10; i++) {
            messages.add(ModelCreater.createModel("Content" + i, i % 2));
        }
        adapter = new OnlineListAdapter(this, messages);
    }

    private class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.onlineServiceImageViewSend:
                    messages.add(ModelCreater
                                     .createModel(onlineServiceEditTextMessage.getText().toString(),
                                                  0));
                    onlineServiceEditTextMessage.getText().clear();
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.onlineServiceImageViewText:
                    onlineServiceTextChat.setVisibility(View.INVISIBLE);
                    break;
                case R.id.onlineServiceImageViewSound:
                    onlineServiceTextChat.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private class LongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.onlineServiceTextViewSound:

                    return true;
            }
            return false;
        }
    }
}
