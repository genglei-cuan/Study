package cn.steve.deeplink;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/5/20.
 */
public class APKCommentReadActivity extends AppCompatActivity {

    private TextView textViewMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);

        String packagePath = ReadCommentFromAPK.getPackagePath(this);
        File file = new File(packagePath);
        String s = ReadCommentFromAPK.readApk(file);
        Toast.makeText(APKCommentReadActivity.this, s, Toast.LENGTH_SHORT).show();
        textViewMain.setText(s);
    }
}
