package cn.steve.classloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/1/7.
 */
public class LoaderMainActivity extends AppCompatActivity {

    private TextView textViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);
        StringBuffer stringBuffer = new StringBuffer();
        for (ClassLoader classLoader = getClassLoader(); classLoader != null; classLoader = classLoader.getParent()) {
            stringBuffer.append(classLoader.getClass()).append(" : ").append(classLoader.toString()).append("\n");
        }
        textViewMain.setText(stringBuffer.toString());

    }
}
