package cn.steve.dagger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yantinggeng on 2016/5/6.
 */
public class ClassRoom {

    Boy boy;

    @Singleton
    @Inject
    public ClassRoom(Boy boy) {
        this.boy = boy;
    }
}
