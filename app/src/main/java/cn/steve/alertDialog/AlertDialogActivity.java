package cn.steve.alertDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import cn.steve.study.R;

/**
 * 对话框
 *
 * @author steve.yan
 */
public class AlertDialogActivity extends Activity {

    String[] allDivisionNames = {"one", "two", "three"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        Button btn = (Button) findViewById(R.id.buttonMain);
        btn.setOnClickListener(new OnClickListener() {
            View contentView = null;

            @Override
            public void onClick(View arg0) {
                newDialog();
            }

            private void singleChooseDialog() {
                new AlertDialog.Builder(AlertDialogActivity.this).setTitle("title").setSingleChoiceItems(allDivisionNames, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Toast.makeText(getApplicationContext(), allDivisionNames[which], Toast.LENGTH_SHORT).show();
                        } finally {
                            dialog.dismiss();
                        }
                    }
                }).show();
            }

            private void traditionalDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
                builder.setTitle("提示");
                builder.setMessage("这是一个普通的对话框！");
                builder.setIcon(R.drawable.ic_launcher);
                builder.setCancelable(false);
                builder.setPositiveButton("知道了！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }

            private void newDialog() {
                contentView = LayoutInflater.from(AlertDialogActivity.this).inflate(R.layout.activity_main_imageview, null);
                contentView.findViewById(R.id.prescale_iamgeview).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animation shake = AnimationUtils.loadAnimation(AlertDialogActivity.this, R.anim.shake);//加载动画资源文件
                        if (contentView != null) {
                            contentView.startAnimation(shake);
                        }
                    }
                });
                Dialog dialog = new Dialog(AlertDialogActivity.this);
                dialog.setContentView(contentView);
                dialog.show();
            }

        });
    }
}
