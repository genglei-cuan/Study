package cn.steve.dagger;

import javax.inject.Inject;

/**
 * Created by yantinggeng on 2016/5/6.
 */
public class ClassRoom {

    Boy boy;

    @Inject
    public ClassRoom(Boy boy) {
        this.boy = boy;
    }
}
