package cn.steve.gallery;

import android.app.Activity;
import android.os.Bundle;

import cn.steve.study.R;

/**
 * Created by Steve on 2015/10/12.
 */
public class MyGalleryMainActivity extends Activity {

    private MyGallery myMainGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygallery);
        this.myMainGallery = (MyGallery) findViewById(R.id.myMainGallery);
    }
}
