package cn.steve.rxandroid;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import cn.steve.study.BuildConfig;
import cn.steve.study.R;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 * Created by Steve on 2016/1/3.
 */
public class RXAndroidActivity extends AppCompatActivity {

    Observable<Integer> deferObservable;
    private EditText editTextRXAndroid, email, phone, username;
    private TextView textViewRxAndroid;
    private Button LoginButton, rxAndroidTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //active the strict mode to detect our sensitive action on thread
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
        setContentView(R.layout.activity_rxandroid);
        editTextRXAndroid = (EditText) findViewById(R.id.editTextRXAndroid);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        username = (EditText) findViewById(R.id.username);
        textViewRxAndroid = (TextView) findViewById(R.id.textViewRxAndroid);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        rxAndroidTestButton = (Button) findViewById(R.id.rxAndroidTestButton);
        simpleDebounce();
        simpleCombineLatest();
        simpleMerge();
        simpleConcat();
        simpleTimer();
//        rxAndroidTestButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deferObservable.subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        System.out.println(integer);
//                    }
//                });
//            }
//        });
//        simpleDefer();
    }

    //两个事件源的时间间隔小于规定的时间单位的，都会被忽略。
    private void simpleDebounce() {
        RxTextView.textChangeEvents(editTextRXAndroid)
            .debounce(400, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TextViewTextChangeEvent>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                    textViewRxAndroid.setText(textViewTextChangeEvent.text());
                }
            });
    }

    //TODO Retrofit结合RxJava做网络请求框架
    //TODO RxJava代替EventBus进行数据传递：RxBus
    //使用combineLatest合并最近N个结点
    private void simpleCombineLatest() {
        Observable<CharSequence> emailChangeObservable = RxTextView.textChanges(email);
        Observable<CharSequence> phoneChangeObservable = RxTextView.textChanges(phone);
        Observable<CharSequence> usernameChangeObservable = RxTextView.textChanges(username);
        Observable.combineLatest(emailChangeObservable, phoneChangeObservable, usernameChangeObservable, new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
                if (!TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(charSequence2) && !TextUtils.isEmpty(charSequence3)) {
                    return true;
                } else {
                    return false;
                }
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    LoginButton.setEnabled(true);
                } else {
                    LoginButton.setEnabled(false);
                }
            }
        });

    }

    //使用merge合并两个数据源。
    private void simpleMerge() {
        Observable.merge(getDataFromFile(), getDataFromNet())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    textViewRxAndroid.setText(s + textViewRxAndroid.getText());
                }
            });
    }

    private Observable<String> getDataFromNet() {
        return Observable.just("Net1");
    }

    private Observable<String> getDataFromFile() {
        return Observable.just("File1");
    }

    //使用concat和first做缓存,依次检查memory、disk和network中是否存在数据，任何一步一旦发现数据后面的操作都不执行。
    private void simpleConcat() {

        Observable<String> memory = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String memoryCache = "memory cache";
                if (memoryCache != null) {
                    subscriber.onNext(memoryCache);
                } else {
                    subscriber.onCompleted();
                }
            }
        });
        Observable<String> disk = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String cachePref = "disk cache";
                if (!TextUtils.isEmpty(cachePref)) {
                    subscriber.onNext(cachePref);
                } else {
                    subscriber.onCompleted();
                }
            }
        });

        Observable<String> network = Observable.just("network");

        //依次检查memory、disk、network,假如没有任何满足要求的数据，会抛出NoSuchElementException
        Observable.concat(memory, disk, network)
            .first()
            .subscribeOn(Schedulers.newThread())
            .subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    System.out.println("选择了：" + s);
                }
            });
        //依次检查memory、disk、network,没有任何数据就会当作已完成
        Observable.concat(memory, disk, network)
            .takeFirst(new Func1<String, Boolean>() {
                @Override
                public Boolean call(String s) {
                    // TODO: 16-7-24 判断内容是否是最新的数据
                    return null;
                }
            })
            .subscribeOn(Schedulers.newThread())
            .subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    System.out.println("选择了：" + s);
                }
            });

        //依次检查memory、disk、network
        Observable.concat(memory, disk, network)
            .first(new Func1<String, Boolean>() {
                @Override
                public Boolean call(String s) {
                    // TODO: 16-7-24 判断内容是否是最新的数据
                    return null;
                }
            })
            .subscribeOn(Schedulers.newThread())
            .subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    System.out.println("选择了：" + s);
                }
            });
    }

    //使用timer做定时操作。当有“x秒后执行y操作”类似的需求的时候，想到使用timer
    private void simpleTimer() {
        Observable.timer(2, TimeUnit.SECONDS)
            .subscribe(new Observer<Long>() {
                @Override
                public void onCompleted() {
                    System.out.println("现在是2秒之后");
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Long aLong) {

                }
            });

    }

    //使用interval做周期性操作。当有“每隔xx秒后执行yy操作”类似的需求的时候，想到使用interval
    private void simpleInterval() {
        Observable.interval(2, TimeUnit.SECONDS)
            .subscribe(new Observer<Long>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(Long number) {
                }
            });
    }

    //使用throttleFirst防止按钮重复点击
    //ps：debounce也能达到同样的效果
    private void simpleThrottleFirst() {
        RxView.clicks(LoginButton)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe(new Subscriber<Void>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Void aVoid) {

                }
            });
    }

    //使用schedulePeriodically做轮询请求
    private void simpleSchedulePeriodically() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Schedulers.newThread().createWorker()
                    .schedulePeriodically(new Action0() {
                        @Override
                        public void call() {
                            subscriber.onNext("下一个请求");
                        }
                    }, 1000, 2000, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    //统一操作，在IO线程上产生信息，在主线程上进行消息的发送消费,这样就统一了操作
    <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private Observable<Integer> getInt() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        Log.e("GetInt", currentDateandTime);

        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(42);
                subscriber.onCompleted();
            }
        });
    }

    //simple defer
    private void simpleDefer() {
        //defer中的getInt操作等到有人订阅deferObservable的时候才会被执行
        //假如这里不用defer，直接用getInt返回，那么调用simpleDefer的时候就会打印时间
        deferObservable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return getInt();
            }
        });
//        deferObservable.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("subscribe:" + integer);
//            }
//        });
    }



}
