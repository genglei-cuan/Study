package cn.steve.share;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/10.
 */
public class ShareDialog extends Dialog {

    private Context mContext;

    public ShareDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
        init();
    }

    public ShareDialog(Context context) {
        super(context);
        this.mContext = context;
        init();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    //初始化控件，设置监听器
    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_share, null);
        LinearLayout
            lysharemoreoption =
            (LinearLayout) view.findViewById(R.id.ly_share_more_option);
        LinearLayout lysharecopylink = (LinearLayout) view.findViewById(R.id.ly_share_copy_link);
        LinearLayout lyshareqq = (LinearLayout) view.findViewById(R.id.ly_share_qq);
        LinearLayout lysharesinaweibo = (LinearLayout) view.findViewById(R.id.ly_share_sina_weibo);
        LinearLayout lyshareweichat = (LinearLayout) view.findViewById(R.id.ly_share_weichat);
        LinearLayout
            lyshareweichatcircle =
            (LinearLayout) view.findViewById(R.id.ly_share_weichat_circle);

        setContentView(view);

    }

    private class OnclickShareListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ly_share_weichat_circle:
                    break;

            }
        }

        private void share2QQ() {
        }

        private void share2WeChat() {
        }

    }


}
