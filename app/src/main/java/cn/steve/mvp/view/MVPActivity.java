package cn.steve.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import cn.steve.mvp.bean.User;
import cn.steve.mvp.presenter.UserLoginPresenter;
import cn.steve.study.R;

/**
 * Created by yantinggeng on 2015/12/29.
 */
public class MVPActivity extends AppCompatActivity implements IUserLoginView {

    private android.widget.EditText username;
    private android.widget.EditText password;
    private android.widget.Button button;
    private android.widget.Button buttonClear;
    private android.widget.ProgressBar progress;
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        this.progress = (ProgressBar) findViewById(R.id.progress);
        this.button = (Button) findViewById(R.id.button);
        this.password = (EditText) findViewById(R.id.password);
        this.username = (EditText) findViewById(R.id.username);
        buttonClear= (Button) findViewById(R.id.buttonClear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return username.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void clearUserName() {
        username.getText().clear();
    }

    @Override
    public void clearPassword() {
        password.getText().clear();
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUsername() +
                             " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                       "login failed", Toast.LENGTH_SHORT).show();
    }
}
