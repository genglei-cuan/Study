package cn.steve.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/5/6.
 */
public class Dagger2SimpleActivity extends AppCompatActivity {

    @Inject
    ClassRoom classRoom;
    @Inject
    ClassRoom classRoom2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        TextView textViewMain = (TextView) findViewById(R.id.textViewMain);
        ClassRoomComponent component = DaggerClassRoomComponent.create();
        component.inject(this);
        assert textViewMain != null;
        textViewMain.setText(classRoom.toString() + classRoom2.toString());
    }
}
