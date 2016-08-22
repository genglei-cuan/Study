package cn.steve.ipc.bundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/8/22.
 */
public class BundleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        Button buttonMain = (Button) findViewById(R.id.buttonMain);
        assert buttonMain != null;
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BundleActivity.this, BundleActivityB.class);
                Bundle bundle = new Bundle();
                ShuaiGe shuaiGe = new ShuaiGe("steve", "yan");
                bundle.putParcelable("extra", shuaiGe);
                System.out.println("A:shuaiGe:" + shuaiGe.toString());
                System.out.println("A:bundle:" + bundle.toString());
                intent.putExtra("bundle", bundle);
                BundleActivity.this.startActivity(intent);
            }
        });
    }
}
