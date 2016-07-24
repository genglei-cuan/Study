package cn.steve.animator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import cn.steve.bottomsheet.BottomSheetSimpleActivity;
import cn.steve.study.R;
import steve.cn.mylib.util.CircularAnimUtil;

public class CircularAnimUtilActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    Button mChangeBtn, mActivityImageBtn, mActivityColorBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_anim_util);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mChangeBtn = (Button) findViewById(R.id.change_btn);
        mActivityImageBtn = (Button) findViewById(R.id.activity_image_btn);
        mActivityColorBtn = (Button) findViewById(R.id.activity_color_btn);

        mChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                // 收缩按钮
                CircularAnimUtil.hide(mChangeBtn);
            }
        });

        mProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.GONE);
                // 伸展按钮
                CircularAnimUtil.show(mChangeBtn);
            }
        });

        mActivityImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 先将图片展出铺满，然后启动新的Activity
                CircularAnimUtil.startActivity(CircularAnimUtilActivity.this, BottomSheetSimpleActivity.class, view, R.drawable.xihu);
            }
        });

        mActivityColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 先将颜色展出铺满，然后启动新的Activity
                CircularAnimUtil.startActivity(CircularAnimUtilActivity.this, BottomSheetSimpleActivity.class, view, R.color.colorPrimary);
            }
        });
    }
}
