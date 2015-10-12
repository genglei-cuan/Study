package cn.steve.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Gallery;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/10/12.
 */
public class GalleryMainActivity extends Activity {

    Gallery gallery;
    GalleryStringAdapter adapter;
    ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_main);
        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setGravity(Gravity.CENTER_HORIZONTAL);      // 设置水平居中显示
        gallery.setUnselectedAlpha(0.3f);                    // 设置未选中图片的透明度
        gallery.setSpacing(40);                              // 设置图片之间的间距
        for (int i = 0; i < 365; i++) {
            datas.add("" + i);
        }
        adapter = new GalleryStringAdapter(datas, this);
        gallery.setAdapter(adapter);
    }
}
