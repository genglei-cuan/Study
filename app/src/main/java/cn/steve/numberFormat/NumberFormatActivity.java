package cn.steve.numberFormat;

import android.app.Activity;
import android.os.Bundle;

import java.text.DecimalFormat;

/**
 * 解决西班牙地区的小数点问题
 *
 * @author Steve
 */
public class NumberFormatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DecimalFormat df = new DecimalFormat("0.0");

        final String format = df.format(52.36);
        System.out.println("format:" + format);

        float f = (float) (Math.round((float) 52.36 * 10)) / 10;
        System.out.println("f:" + f);

    }

}
