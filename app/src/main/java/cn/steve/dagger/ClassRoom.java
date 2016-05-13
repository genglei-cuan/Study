package cn.steve.dagger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by yantinggeng on 2016/5/6.
 */
public class ClassRoom {

    @Named("noname")
    @Inject
    Boy boy2;
    Boy boy;

    @Inject
    public ClassRoom(@Named("name") Boy boy) {
        this.boy = boy;
    }

    public Boy getBoy2() {
        return boy2;
    }

    public Boy getBoy() {
        return boy;
    }

}
