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
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 介绍RXAndroid使用的demo,这些内容很多与JavaTest中的雷同，权当再次熟悉API
 * <p/>
 * <p/>
 * Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。RxJava 使用 create() 方法来创建一个 Observable ，并为它定义事件触发规则。
 * <p/>
 * Observable 并不是在创建的时候就立即开始发送事件，而是在它被订阅的时候，即当 subscribe() 方法执行的时候。
 * <p/>
 * Created by yantinggeng on 2015/11/4.
 * <p/>
 * 内容和大部分的注释来自于： http://gank.io/post/560e15be2dca930e00da1083
 */


public class RXJavaActivity extends AppCompatActivity {

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

    // 在不指定线程的情况下， RxJava 遵循的是线程不变的原则，
    // 即：在哪个线程调用 subscribe()，就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。
    // 如果需要切换线程，就需要用到 Scheduler （调度器）。
    private void simpleScheduler() {

        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())//指定 subscribe() 发生在 IO 线程,即订阅发生在IO线程，那么事件的产生就在IO线程
                .observeOn(AndroidSchedulers.mainThread())//指定 Subscriber 的回调发生在主线程，即事件的消费在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        textViewMain.setText("" + integer);
                    }
                });


    }

    //变化：所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。
    //实现的是一对一的转换
    // Func1 的类。它和 Action1 非常相似，也是 RxJava 的一个接口，用于包装含有一个参数的方法。
    // Func1 和 Action 的区别在于， Func1 包装的是有返回值的方法。另外，和 ActionX 一样， FuncX 也有多个，用于不同参数个数的方法。
    // FuncX 和 ActionX 的区别在 FuncX 包装的是有返回值的方法。
    private void simpleMap() {

        //实现转换的函数,传进来的参数是String,目标转换的类型是Integer
        Func1<String, Integer> func1 = new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return 1;
            }
        };

        //订阅者，接收处理的参数类型是Integer
        Action1<Integer> action1 = new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        };

        Observable.just("SimpleMap").map(func1).subscribe(action1);

    }


    //返回一个Observable 对象
    //flatMap() 和 map() 有一个相同点：它也是把传入的参数转化之后返回另一个对象。
    // 但需要注意，和 map() 不同的是， flatMap() 中返回的是个 Observable 对象，并且这个 Observable 对象并不是被直接发送到了 Subscriber 的回调方法中。
    // flatMap() 的原理是这样的：1. 使用传入的事件对象创建一个 Observable 对象；
    // 2. 并不发送这个 Observable, 而是将它激活，于是它开始发送事件；
    // 3. 每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法。
    //  这三个步骤，把事件拆成了两级，通过一组新创建的 Observable 将初始的对象『铺平』之后通过统一路径分发了下去。
    // 而这个『铺平』就是 flatMap() 所谓的 flat。
    private void simpleFlatMap() {
        Course[] courses = {new Course(1, "A"), new Course(2, "B"), new Course(3, "C")};
        Course[] courses2 = {new Course(4, "D"), new Course(5, "E"), new Course(6, "F")};
        Student[] students = {new Student(1, courses), new Student(2, courses2)};

        //事件的处理者
        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Course course) {
                System.out.println(course.getCname());
            }
        };

        //实现转换，返回的是一个Observable,里面包含了所有的事件
        Func1<Student, Observable<Course>> func1 = new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourses());
            }
        };

        Observable.from(students).flatMap(func1).subscribe(subscriber);
    }

    private void simpleLift() {
        //flatMap的本质就是lift，期间创建一个新的Observable和一个新的Subscriber
        //每次flat一下，就增加一对
    }

    private void simpleCompose() {
        //和 lift() 的区别在于， lift() 是针对事件项和事件序列的，而 compose() 是针对 Observable 自身进行变换。
        Observable<Integer> observable = Observable.just(1, 2);

        observable.compose(new Observable.Transformer<Integer, String>() {
            @Override
            public Observable<String> call(Observable<Integer> integerObservable) {
                //对integerObservable进行任意次的lift，而后返回
                return null;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    //doOnSubscribe,subscribe()调用后而且在事件发送前执行，能指定线程
    private void simpleDoOnSubscribe() {
        Observable.just("sss")
                .subscribeOn(Schedulers.io()) //指定订阅发生在IO线程
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("准备工作是在主线程执行的哦！");
                    }
                })
                .subscribeOn(
                        AndroidSchedulers.mainThread())//doOnSubscribe()的后面跟一个 subscribeOn() ，就能指定准备工作的线程了。
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //在IO线程执行的订阅
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


    private void simpleCompose2() {
        Observable<Integer> observable = Observable.just(1, 2);
        observable.compose(applySchedulers()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });
    }


    private void simpleRxRetrofit() {

    }


    //2015结束
    //TODO 接下来是2016的代码


}


