package cn.steve.dagger.school;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.steve.study.R;
import dagger.ObjectGraph;

/**
 * 基于：http://blog.csdn.net/ljphhj/article/details/37663071
 *
 *
 * Created by yantinggeng on 2015/11/19.
 */
public class DaggerSchoolActivity extends AppCompatActivity {

    private TextView textViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);
        ObjectGraph objectGraph = ObjectGraph.create(new AppModules());
        ClassRoom classRoom = objectGraph.get(ClassRoom.class);
        classRoom.study();
    }


}
