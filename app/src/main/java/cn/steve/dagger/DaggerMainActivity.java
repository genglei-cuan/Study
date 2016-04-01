package cn.steve.dagger;

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
  ShoppingCartModel cartModel;

  private ActivityComponent mActivityComponent;
  private TextView daggerTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dagger);
    this.daggerTextView = (TextView) findViewById(R.id.daggerTextView);
    mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();

    ContainerComponent containerComponent = DaggerContainerComponent.builder()
        .activityComponent(mActivityComponent).containerModule(new ContainerModule()).build();
    containerComponent.inject(this);

    daggerTextView.setText(userModel.name + userModel.gener);
  }
}
