# agera 入门简介
    agera 作为google官方的响应式编程库被提出，在此尝试，也算与rxjava做一个简单比较。

## 术语:
Observable和Updatable,
Updatable指代的是观察者模式中的观察者，而Observable所指代的就是观察者模式中的被观察者。
整个agera就是建立在［使用Updatable去观察Observable，Observable去通知Updatable更新］的基础上进行开发的。
具体到代码就是使用Observable的addUpdatable()方法去将Updatable注册到Observable中，并且在合适的实际调用Updatable的update()方法去通知Updatable更新。
Supplier：agera中提供数据的接口，通过范型指定数据类型，通过get()方法获取数据。
Repository：agera中集成了Observable和Supplier功能的一个［提供数据的被观察者］

## 简单demo
```java
public class AgeraMainActivity extends AppCompatActivity implements Updatable {
    @Bind(R.id.buttonMain)
    Button buttonMain;
    private Observable ageraObservable = new Observable() {
        @Override
        public void addUpdatable(@NonNull Updatable updatable) {
            updatable.update();
        }
        @Override
        public void removeUpdatable(@NonNull Updatable updatable) {
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.buttonMain)
    void receivedEvent() {
        ageraObservable.addUpdatable(this);
    }
    //更新的事件通知
    @Override
    public void update() {
        Toast.makeText(AgeraMainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
    }
}
```
当前的activity实现 Updatable 接口，点击按钮的时候，将当前的activity注册给Observable对象，发送更新事件，接着会调用Updatable的update方法。

上面的实现很简单，但是发现一个问题，这个接口他只能发送更新事件，通知更新，但是，不能传递数据！

google设计这个库的原则就是"Push event,pull data",而在rxjava中是 "push data in RX"，在之前理解rxjava的时候，就讲到这个事件和数据的关系。
所以，提供了另外的方案，实现pull data。




## 参考
1. [要做一个有冒险精神的人！开启漫漫的agera之旅](http://zjutkz.net/2016/04/23/%E8%A6%81%E5%81%9A%E4%B8%80%E4%B8%AA%E6%9C%89%E5%86%92%E9%99%A9%E7%B2%BE%E7%A5%9E%E7%9A%84%E4%BA%BA%EF%BC%81%E5%BC%80%E5%90%AF%E6%BC%AB%E6%BC%AB%E7%9A%84agera%E4%B9%8B%E6%97%85/)
1. [官方wiki](https://github.com/google/agera/wiki)
