package cn.steve.dagger;

import dagger.Component;

/**
 * Created by yantinggeng on 2016/5/6.
 */

@PerActivity
@Component(modules = BoysModule.class)
public interface ClassRoomComponent {

    void inject(Dagger2SimpleActivity activity);

}

