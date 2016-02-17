package cn.steve.alertDialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/2/17.
 */
public class ShareSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        ShareSuccessDialog dialog = ShareSuccessDialog.buildDialog(this, 3, new ShareSuccessDialog.ShareSuccessResultModel("Hello", "5.5"));
        dialog.show();
    }
}
