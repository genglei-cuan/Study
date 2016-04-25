package cn.steve.agera;

import com.google.android.agera.Observable;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Supplier;
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
public class AgeraMainActivity extends AppCompatActivity implements Updatable {

    @Bind(R.id.buttonMain)
    Button buttonMain;

    private Observable ageraObservable;
    private Supplier<String> supplier;
    private Repository<String> repository;

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

        supplier = new Supplier<String>() {
            @NonNull
            @Override
            public String get() {
                System.out.println("data.....");
                return "data from supplier";
            }
        };
        repository = Repositories.repositoryWithInitialValue("a")
            .observe()
            .onUpdatesPerLoop()
            .thenGetFrom(supplier)
            .compile();
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
        Toast.makeText(AgeraMainActivity.this, repository.get(), Toast.LENGTH_SHORT).show();
    }

}
