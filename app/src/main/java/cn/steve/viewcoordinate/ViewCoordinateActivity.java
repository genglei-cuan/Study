package cn.steve.viewcoordinate;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * 测试android中的view的坐标系
 *
 * Created by yantinggeng on 2015/10/20.
 */
public class ViewCoordinateActivity extends AppCompatActivity {

    private android.widget.TextView textviewUP;
    private android.widget.TextView textviewDown;
    private Button buttonChangePostion;

    //记录绝对坐标
    private int[] positionInWindow = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate);
        this.textviewDown = (TextView) findViewById(R.id.textviewDown);
        this.textviewUP = (TextView) findViewById(R.id.textviewUP);
        buttonChangePostion = (Button) findViewById(R.id.buttonChangePostion);
        buttonChangePostion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePositon(270);
            }
        });

        textviewUP.getLocationInWindow(positionInWindow);

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void changePositon(int degree) {

        int width = textviewDown.getWidth();
        int height = textviewDown.getHeight();

        int left = (int) textviewDown.getX();
        int top = (int) textviewDown.getY();
        int bottom = top + textviewDown.getHeight();
        int right = left + textviewDown.getWidth();

        int x = left + width / 2;
        int y = top + height / 2;

        int d = (int) (width / 2 * Math.cos(degree));
        int e = (int) (width / 2 * Math.sin(degree));

        switch (degree) {
            //cos值，sin值均为正值
            case 0:
                textviewUP.setX(x);
                textviewUP.setY(top - textviewUP.getHeight());
                break;
            case 30:
                e = (int) (width / 2 * Math.sin(degree));
                textviewUP.setX(x - e);
                textviewUP.setY(top - textviewUP.getHeight());
                break;

            case 60:
                textviewUP.setX(right);
                textviewUP.setY(y - textviewUP.getHeight() / 2);
                break;

            case 90:
                d = (int) (width / 2 * Math.cos(degree));
                e = (int) (width / 2 * Math.sin(degree));
                textviewUP.setX(right);
                textviewUP.setY(y - d - textviewUP.getHeight());
                break;

            case 120:
                textviewUP.setX(right);
                textviewUP.setY(y + width / 4);
                break;

            case 150:
                textviewUP.setX(right - width / 4);
                textviewUP.setY(bottom);
                break;

            case 180:
                textviewUP.setX(x - textviewUP.getWidth() / 2);
                textviewUP.setY(bottom);
                break;

            case 210:
                textviewUP.setX(x - textviewUP.getWidth() / 2 - width / 4);
                textviewUP.setY(bottom);
                break;

            case 240:
                textviewUP.setX(x - textviewUP.getWidth() / 2 - width / 2);
                textviewUP.setY(bottom);
                break;

            case 270:
                textviewUP.setX(left - textviewUP.getWidth());
                textviewUP.setY(y);
                break;

            case 300:
                textviewUP.setX(left - textviewUP.getWidth());
                textviewUP.setY(y - width / 4);
                break;

            case 330:
                textviewUP.setX(left - textviewUP.getWidth());
                textviewUP.setY(y - width / 2);
                break;


        }

//        if (0 <= degree & degree < 90) {
//
//            //cos值，sin值均为正值
//            d = (int) (width / 2 * Math.cos(degree));
//            e = (int) (width / 2 * Math.sin(degree));
//
//            textviewUP.setX(x + e);
//            textviewUP.setY(y - d - textviewUP.getHeight());
//
//        }
//        if (90 <= degree & degree < 180) {
//            //cos值为负值，sin值为正值
//            d = (int) (width / 2 * Math.cos(degree));
//            e = (int) (width / 2 * Math.sin(degree));
//
//            textviewUP.setX(x - d);
//            textviewUP.setY(y + e);
//        }
//        if (180 <= degree & degree < 270) {
//            //cos值为负值，sin值为负值
//            d = (int) (width / 2 * Math.cos(degree));
//            e = (int) (width / 2 * Math.sin(degree));
//
//            textviewUP.setX(x + d - textviewUP.getWidth());
//            textviewUP.setY(y - e);
//        }
//        if (270 <= degree & degree < 360) {
//            //cos值为正值，sin值为负值
//            d = (int) (width / 2 * Math.cos(degree));
//            e = (int) (width / 2 * Math.sin(degree));
//
//            textviewUP.setX(x - d - textviewUP.getWidth());
//            textviewUP.setY(y - e - textviewUP.getHeight());
//        }

//        textviewUP.setX(left - textviewUP.getWidth());
//        textviewUP.setY(bottom);

    }


}
