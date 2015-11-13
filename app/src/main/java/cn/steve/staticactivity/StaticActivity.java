package cn.steve.staticactivity;

import android.app.Activity;
import android.os.Bundle;

import java.util.Locale;

/**
 * 1. 先执行static代码段 2. 普通代码段 3. onCreate
 *
 * @author Steve
 */
public class StaticActivity extends Activity {

    private static String url;

    static {
        if (Locale.getDefault().getLanguage().equals("zh")) {
            url = "www.baidu.com";
        } else {
            url = "www.google.com";
        }
    }

    {
        System.out.println("代码段");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        System.out.println(url);

    }

}
