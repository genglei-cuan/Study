package cn.steve.customdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/8/19.
 */
public class FullScreenDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        Button buttonMain = (Button) findViewById(R.id.buttonMain);

        assert buttonMain != null;
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FullScreenDialog dialog = new FullScreenDialog(FullScreenDialogActivity.this);
                //dialog.setTitle("HHHHH");
                //dialog.show();
                Dialog dialog = new Dialog(FullScreenDialogActivity.this);
                dialog.getContext().setTheme(R.style.Dialog_Fullscreen);
                dialog.setContentView(R.layout.activity_dialogu_main);
                dialog.show();
            }
        });
    }
}
