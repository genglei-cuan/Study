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

  @Inject
  Master master;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_textview);
    TextView textViewMain = (TextView) findViewById(R.id.textViewMain);
    MasterComponent masterComponent = DaggerMasterComponent.builder()
        .masterModule(new MasterModule())
        .build();
    BoyComponent boyComponent = DaggerBoyComponent.builder()
        .boysModule(new BoysModule())
        .masterComponent(masterComponent)
        .build();
    ClassRoomComponent classRoomComponent = DaggerClassRoomComponent.builder()
        .boyComponent(boyComponent)
        .build();
    classRoomComponent.inject(this);
    String text = classRoom.getBoy().getName() + "\n" +
                  classRoom2.getBoy().getName() + "\n" +
                  classRoom.getBoy2().getName() + "\n" +
                  master.getName().toString() + "\n";
    textViewMain.setText(text);
  }
}
