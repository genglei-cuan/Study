package cn.steve.dagger;

import dagger.Component;

/**
 * modules声明了提供依赖，inject的参数声明了消费的依赖，所以二者是就考component连接
 *
 * Created by Steve on 2015/12/27.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {


    //inject方法需要一个消耗依赖的类型对象作为参数。次数不能写成activity
    void inject(DaggerMainActivity activity);
}
