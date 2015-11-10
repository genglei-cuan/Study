package cn.steve.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import cn.steve.study.R;

/**
 * 将activity看做是view
 *
 * Created by yantinggeng on 2015/11/10.
 */
public class MVPMainActivity extends AppCompatActivity
    implements MVPLoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    //逻辑执行类
    private MVPLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);
        presenter = new MVPLoginPresenterImpl(this);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError("error");
    }

    @Override
    public void setPasswordError() {
        password.setError("error");
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(MVPMainActivity.this, "Replace the action here!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        //调用业务逻辑
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}
