package cn.steve.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import cn.steve.study.R;

/**
 * 选择恰当的图像尺寸 图像尺寸不合适会导致自动缩放 避免实时缩放 最好预先缩放到视图大小
 * <p/>
 * Created by Steve on 2015/10/31.
 */
public class ImagePreScaleActivity extends Activity {

    private Bitmap src;
    private Bitmap des;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_imageview);
        imageview = (ImageView) findViewById(R.id.prescale_iamgeview);
        des = Bitmap.createScaledBitmap(src, imageview.getWidth(), imageview.getHeight(), true);
        imageview.setImageBitmap(des);
    }
}
