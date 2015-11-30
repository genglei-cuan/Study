package cn.steve.viewcoordinate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import cn.steve.study.R;
import steve.cn.mylib.commonutil.ScreenUtils;

/**
 * Created by yantinggeng on 2015/11/30.
 */
public class LayoutParamsScrollActivity extends AppCompatActivity {

    private android.widget.Button buttonMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        this.buttonMain = (Button) findViewById(R.id.buttonMain);
        this.buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) buttonMain.getLayoutParams();
                layoutParams.height += ScreenUtils.dpToPxInt(getApplicationContext(), 10);
                System.out.println("宽度：" + layoutParams.width);
                System.out.println("高度：" + layoutParams.height);
                RelativeLayout.LayoutParams layoutParams2 =new RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height);
                buttonMain.setLayoutParams(layoutParams2);
            }
        });
    }


}
