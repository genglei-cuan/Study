package cn.steve.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.steve.study.R;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * 介绍RXAndroid使用的demo
 *
 * Created by yantinggeng on 2015/11/4.
 */
public class RXAndroidActivity extends AppCompatActivity {

    private TextView textViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        textViewMain = (TextView) findViewById(R.id.textViewMain);
        simpleAction();
    }


    //最基本的observer
    private void simpleObserver() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                //执行结束
            }

            @Override
            public void onError(Throwable e) {
                //执行有错误
            }

            @Override
            public void onNext(String s) {
                //传递消息到下一步
                textViewMain.setText(s);
            }
        };


    }


    //简单的使用Action1这个接口，来变现单个参数的观察者参数
    private void simpleAction() {
        String[] words = {"1", "2", "3"};
        Action1 action1 = new Action1() {
            @Override
            public void call(Object o) {
                textViewMain.setText(o.toString());
            }
        };
        Observable.just(words).subscribe(action1);
    }


}
