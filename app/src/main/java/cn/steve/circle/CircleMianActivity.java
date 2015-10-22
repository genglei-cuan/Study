package cn.steve.circle;

import android.app.Activity;
import android.os.Bundle;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/22.
 */
public class CircleMianActivity extends Activity {

    private SleepClockCircle sleepcircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        this.sleepcircle = (SleepClockCircle) findViewById(R.id.sleepcircle);
        sleepcircle.setStartEndAngle(getAngle(12), getDelat(2));
    }


    //获得初始的角度
    private int getAngle(int hour) {
        int num = 0;
        switch (hour % 12) {
            case 0:
                num = 0;
                break;
            case 1:
                num = 30;
                break;
            case 2:
                num = 60;
                break;
            case 3:
                num = 90;
                break;
            case 4:
                num = 120;
                break;
            case 5:
                num = 150;
                break;
            case 6:
                num = 180;
                break;
            case 7:
                num = 210;
                break;
            case 8:
                num = 240;
                break;
            case 9:
                num = 270;
                break;
            case 10:
                num = 300;
                break;
            case 11:
                num = 330;
            case 12:
                num = 360;
                break;
        }
        return num;
    }


    //获取角度差值
    private int getDelat(int l) {
        int delta = 0;
        delta = 30 * l;
        return delta;
    }


}
