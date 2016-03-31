package cn.steve.des;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/3/31.
 */
public class DesMainActivity extends AppCompatActivity {

    private android.widget.TextView textViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);
        String key = "370B5EA754A1A4254C32864A80A154409E469BEC5E6D2F75";
        String text = "admin";
        try {
            String result1 = new String(DES.encryptDES(text));
            String result2 = new String(DES.decryptDES(result1));
            Log.i("DES encode text is ", result1);
            Log.i("DES encode text is ", result2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
