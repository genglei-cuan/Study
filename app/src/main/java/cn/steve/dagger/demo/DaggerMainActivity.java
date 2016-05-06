package cn.steve.dagger.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import cn.steve.study.R;

/**
 * Created by Steve on 2015/12/27.
 */
public class DaggerMainActivity extends AppCompatActivity {

    @Inject
    UserModel userModel;
    @Inject
    UserModel userModel2;
    @Inject
    ShoppingCartModel cartModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        TextView daggerTextView = (TextView) findViewById(R.id.daggerTextView);
        ActivityComponent mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();
        ContainerComponent containerComponent = DaggerContainerComponent.builder()
            .activityComponent(mActivityComponent).containerModule(new ContainerModule())
            .build();
        containerComponent.inject(this);
        assert daggerTextView != null;
        daggerTextView.setText(userModel.name + userModel.gener + "\n" + userModel.toString() + "\n" + userModel2.toString());
    }
}
