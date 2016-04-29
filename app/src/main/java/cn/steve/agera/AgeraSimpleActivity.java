package cn.steve.agera;

import com.google.android.agera.Observable;
import com.google.android.agera.Updatable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/4/25.
 */
public class AgeraSimpleActivity extends AppCompatActivity implements Updatable {

    @Bind(R.id.buttonMain)
    Button buttonMain;

    private Observable ageraObservable;

    private void init() {
        ageraObservable = new Observable() {
            @Override
            public void addUpdatable(@NonNull Updatable updatable) {
                updatable.update();
            }

            @Override
            public void removeUpdatable(@NonNull Updatable updatable) {
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        ButterKnife.bind(this);
        init();
    }


    @OnClick(R.id.buttonMain)
    void receivedEvent() {
        ageraObservable.addUpdatable(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //更新的事件通知
    @Override
    public void update() {
        Toast.makeText(AgeraSimpleActivity.this, "simple ", Toast.LENGTH_SHORT).show();
    }

}
