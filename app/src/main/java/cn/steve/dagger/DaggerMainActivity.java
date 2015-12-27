package cn.steve.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import cn.steve.study.R;

/**
 * Created by Steve on 2015/12/27.
 */
public class DaggerMainActivity extends AppCompatActivity {
    @Inject
    UserModel userModel;
    private ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

    }
}
