package cn.steve.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

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
        this.myMainGallery.setMonSelectedListener(new MyGallery.onSelectedListener() {
            @Override
            public void onSelected(MyGalleryModel model) {
                Log.i("MyGalleryModel:", model.getYear() + "" + model.getMonth() + "" + model.getDay());
            }
        });
    }
}
