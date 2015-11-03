package cn.steve.imageloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/11/3.
 */
public class UILGroupDisplayActivity extends Activity {

    private DisplayImageOptions options;
    private android.widget.TextView textViewMain;
    private View linearLayoutRoot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);
        this.linearLayoutRoot = findViewById(R.id.linearLayoutRoot);
        options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_stub)
            .showImageForEmptyUri(R.drawable.ic_empty)
            .showImageOnFail(R.drawable.ic_error)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

        ImageLoader.getInstance().loadImage(
            "http://b.hiphotos.baidu.com/image/pic/item/32fa828ba61ea8d34c95be1b950a304e251f587e.jpg",
            options,
            new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    BitmapDrawable background = new BitmapDrawable(loadedImage);
                    linearLayoutRoot.setBackgroundDrawable(background);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    super.onLoadingFailed(imageUri, view, failReason);
                    textViewMain.setText(failReason.toString());
                }

                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    super.onLoadingStarted(imageUri, view);
                    textViewMain.setText("start");
                }
            });

    }
}
