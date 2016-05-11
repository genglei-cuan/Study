package cn.steve.dagger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by yantinggeng on 2016/5/6.
 */
public class ClassRoom {

    Boy boy;

    //这里不能正常注入
    @Named("noname")
    Boy boy2;

    @Inject
    public ClassRoom(@Named("name") Boy boy) {
        this.boy = boy;
    }

    public Boy getBoy() {
        System.out.println("boy2:" + boy2.getName());
        return boy;
    }

    public void setBoy(Boy boy) {
        this.boy = boy;
    }
}
