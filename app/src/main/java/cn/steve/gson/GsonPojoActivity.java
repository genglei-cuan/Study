package cn.steve.gson;

import com.google.gson.Gson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/7/25.
 */
public class GsonPojoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        TextView textViewMain = (TextView) findViewById(R.id.textViewMain);
        Gson gson = new Gson();
        GsonPojo gsonPojo = gson.fromJson("{\n"
                                          + "   \"anInt\":1\n"
                                          + "   }", GsonPojo.class);
        textViewMain.setText("" + gsonPojo.aBoolean + gsonPojo.anInt);
    }
}
