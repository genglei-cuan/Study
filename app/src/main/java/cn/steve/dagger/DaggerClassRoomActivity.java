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
public class DaggerClassRoomActivity extends AppCompatActivity {

    @Inject
    ClassRoom classRoom;
    @Inject
    ClassRoom classRoom2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        TextView textViewMain = (TextView) findViewById(R.id.textViewMain);
        BoyComponent boyComponent = DaggerBoyComponent.builder().boysModule(new BoysModule()).build();
        ClassRoomComponent classRoomComponent = DaggerClassRoomComponent.builder().boyComponent(boyComponent).build();
        classRoomComponent.inject(this);
        textViewMain.setText(classRoom.getBoy().getName() + "\n" + classRoom2.getBoy().getName());
    }
}
