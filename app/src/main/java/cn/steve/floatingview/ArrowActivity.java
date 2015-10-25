package cn.steve.floatingview;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Steve on 2015/9/1.
 */
public class ArrowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initView(this);
        setContentView(WindowUtils.showInstallSuccessView("https://www.baidu.com/"));


    }
}
