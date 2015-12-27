package cn.steve.dagger;

import dagger.Component;

/**
 * Created by Steve on 2015/12/27.
 */


@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    //inject方法需要一个消耗依赖的类型对象作为参数。次数不能写成activity
    void inject(DaggerMainActivity activity);
}
