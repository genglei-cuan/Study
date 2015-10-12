package cn.steve.gallery;

import android.app.Activity;
import android.os.Bundle;
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
        for (int i = 0; i < 365; i++) {
            datas.add("" + i);
        }
        adapter = new GalleryStringAdapter(datas, this);
        gallery.setAdapter(adapter);
    }
}
