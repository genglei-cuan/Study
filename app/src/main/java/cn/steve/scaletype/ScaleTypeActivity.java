package cn.steve.scaletype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/11/4.
 */
public class ScaleTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView prescaleiamgeview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_imageview);
        this.prescaleiamgeview = (ImageView) findViewById(R.id.prescale_iamgeview);
    }

    @Override
    public void onClick(View view) {
        //1. 按图片的原来size居中显示，当图片长/宽超过View的长/宽，则截取图片的居中部分显示
        prescaleiamgeview.setScaleType(ImageView.ScaleType.CENTER);
        //2. 按比例扩大图片的size居中显示，使得图片长(宽)等于或大于View的长(宽)
        prescaleiamgeview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //3. 将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长/宽等于或小于View的长/宽
        prescaleiamgeview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //4. 把图片按比例扩大/缩小到View的宽度，居中显示
        prescaleiamgeview.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //5. FIT_START, FIT_END在图片缩放效果上与FIT_CENTER一样，只是显示的位置不同，FIT_START是置于顶部，FIT_CENTER居中，FIT_END置于底部。
        //6. 不按比例缩放图片，目标是把图片塞满整个View。
        prescaleiamgeview.setScaleType(ImageView.ScaleType.FIT_XY);


    }


}
