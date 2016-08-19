package cn.steve.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/8/19.
 */
public class FullScreenDialog extends Dialog {


    private android.widget.TextView dialogTitle;
    private android.widget.ImageView closeView;
    private android.widget.FrameLayout dialogContent;

    public FullScreenDialog(Context context) {
        super(context, R.style.Dialog_Fullscreen);
        setContentView(R.layout.fullscreen_dialog);
        this.dialogContent = (FrameLayout) findViewById(R.id.dialogContent);
        this.closeView = (ImageView) findViewById(R.id.closeView);
        this.dialogTitle = (TextView) findViewById(R.id.dialogTitle);
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FullScreenDialog.this.dismiss();
            }
        });
    }

    public void setDialogContent(View view) {
        this.dialogContent.addView(view);
    }

    public void setTitle(String title) {
        dialogTitle.setText(title);
    }

}
