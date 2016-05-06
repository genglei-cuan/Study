package cn.steve.dagger.demo;

import dagger.Component;

/**
 * Created by yantinggeng on 2015/12/28.
 */
//在component后增加ActivityComponent了dependencies参数，使得一个Component成为了另一个Component的依赖。
@Component(dependencies = ActivityComponent.class, modules = ContainerModule.class)
public interface ContainerComponent {

    void inject(DaggerMainActivity mainActivity);

}
