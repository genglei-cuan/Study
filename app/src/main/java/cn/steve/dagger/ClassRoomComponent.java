package cn.steve.dagger;

import dagger.Component;

/**
 * 只要被依赖的component有scope限制，那么依赖他的component必须有scope修饰，并且二者不能一样
 *
 *
 * Created by yantinggeng on 2016/5/6.
 */

@Component(dependencies = {BoyComponent.class})
@PerClassRoom
public interface ClassRoomComponent {

    void inject(DaggerClassRoomActivity activity);
}

