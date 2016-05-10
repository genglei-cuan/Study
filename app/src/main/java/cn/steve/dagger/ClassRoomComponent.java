package cn.steve.dagger;

import dagger.Component;

/**
 * Created by yantinggeng on 2016/5/6.
 */

@Component(dependencies = {BoyComponent.class})
public interface ClassRoomComponent {

  void inject(DaggerClassRoomActivity activity);
}

