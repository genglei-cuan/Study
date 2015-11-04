package cn.steve.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.steve.study.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 介绍RXAndroid使用的demo,这些内容很多与JavaTest中的雷同，权当再次熟悉API
 *
 *
 * Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。RxJava 使用 create() 方法来创建一个 Observable ，并为它定义事件触发规则。
 *
 * Observable 并不是在创建的时候就立即开始发送事件，而是在它被订阅的时候，即当 subscribe() 方法执行的时候。
 *
 * Created by yantinggeng on 2015/11/4.
 *
 * 内容和大部分的注释来自于： http://gank.io/post/560e15be2dca930e00da1083
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


    //最基本的使用方式
    private void simpleObserver() {

        //创建订阅者,Observer 即观察者，它决定事件触发的时候将有怎样的行为。
        Subscriber<String> observer = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
                //还未发送消息之前调用
            }

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

        //创建Observable
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            //事件的触发规则
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("he1");
                subscriber.onNext("he2");
                subscriber.onNext("he3");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(observer);
    }


    // 快捷创建事件队列: just:将传入的参数依次发送出来
    private void simpleJust() {
        String[] words = {"1", "2", "3"};
        Subscriber subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("simple just :" + s);
            }
        };

        Observable.just(words).subscribe(subscriber);
    }

    // 快捷创建事件队列: from:将传入的数组或 Iterable 拆分成具体对象后，依次发送出来。
    private void simpleFrom() {
        Action1 action1 = new Action1() {
            @Override
            public void call(Object o) {
                textViewMain.setText(o.toString());
            }
        };
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable = Observable.from(words);
        observable.subscribe(action1);
    }


    //简单的使用action这个接口
    private void simpleAction() {
        //简单的使用Action1这个接口，来变现单个参数的观察者参数
        //所有只含有一个参数的回调都可以用这个简单的接口
        Action1 onNextAction = new Action1() {
            @Override
            public void call(Object o) {
                System.out.println("next:" + o.toString());
            }
        };

        Action1 onErrorAction = new Action1() {
            @Override
            public void call(Object o) {
                System.out.println("error:" + o.toString());
            }
        };
        //对于无参的回调，则可以用Action0这个接口简单的实现
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                System.out.println("Complete");
            }
        };
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable = Observable.from(words);
        observable.subscribe(onNextAction);
        observable.subscribe(onNextAction, onErrorAction);
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    //在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：在哪个线程调用 subscribe()，就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）。
    private void simpleScheduler() {

        Observable.just(1, 2, 3, 4)
            .subscribeOn(Schedulers.io())//指定 subscribe() 发生在 IO 线程
            .observeOn(AndroidSchedulers.mainThread())//指定 Subscriber 的回调发生在主线程
            .subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    textViewMain.setText("" + integer);
                }
            });


    }
}


