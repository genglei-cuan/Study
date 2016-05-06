package cn.steve.dagger;

import dagger.Component;

/**
 * Created by yantinggeng on 2016/5/6.
 */

@Component()
public interface ClassRoomComponent {

    void inject(Dagger2SimpleActivity activity);

}

