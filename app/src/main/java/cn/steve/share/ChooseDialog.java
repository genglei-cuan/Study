package cn.steve.share;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/10.
 */
public class ChooseDialog extends Dialog implements View.OnClickListener{

    private android.widget.Button shareButtonQQ;
    private android.widget.Button shareButtonQQZone;
    private android.widget.Button shareButtonWeixin;
    private android.widget.Button shareButtonWeixinTimeLine;
    private android.widget.Button shareButtonWeixinFaverate;

    protected ChooseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ChooseDialog(Context context, int theme) {
        super(context, theme);
    }

    public ChooseDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose);
        this.shareButtonWeixinFaverate = (Button) findViewById(R.id.shareButtonWeixinFaverate);
        this.shareButtonWeixinTimeLine = (Button) findViewById(R.id.shareButtonWeixinTimeLine);
        this.shareButtonWeixin = (Button) findViewById(R.id.shareButtonWeixin);
        this.shareButtonQQZone = (Button) findViewById(R.id.shareButtonQQZone);
        this.shareButtonQQ = (Button) findViewById(R.id.shareButtonQQ);


    }

    @Override
    public void onClick(View view) {

    }
}
