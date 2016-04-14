package cn.steve.log;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import steve.cn.mylib.log.SteveLog;

/**
 * Created by yantinggeng on 2016/4/14.
 */
public class LogActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.init();
//        SteveLog.d("dddd");
//        SteveLog.dNoMethod("dNoMethod");
//        SteveLog.dNoThread("dNoThread");
        SteveLog.dAll("do All");
    }
}
