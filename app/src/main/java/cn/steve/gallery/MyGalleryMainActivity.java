package cn.steve.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import cn.steve.study.R;

/**
 * Created by Steve on 2015/10/12.
 */
public class MyGalleryMainActivity extends Activity {

    private MyGallery myMainGallery;
    private Button changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygallery);
        this.myMainGallery = (MyGallery) findViewById(R.id.myMainGallery);
        this.changeButton = (Button) findViewById(R.id.changeButton);
        this.myMainGallery.setMonSelectedListener(new MyGallery.onSelectedListener() {
            @Override
            public void onSelected(MyGalleryModel model) {
                Log.i("MyGalleryModel:",
                      model.getYear() + "" + model.getMonth() + "" + model.getDay());
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Calendar calendar = Calendar.getInstance();
                                                calendar.set(Calendar.YEAR, 2015);
                                                calendar.set(Calendar.MONTH, 8);
                                                calendar.set(Calendar.DAY_OF_MONTH, 2);
                                                myMainGallery.setmCalendar(calendar);
                                            }
                                        }
        );
    }
}
