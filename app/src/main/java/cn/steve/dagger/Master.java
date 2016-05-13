package cn.steve.dagger;

import javax.inject.Singleton;

/**
 * Created by yantinggeng on 2016/5/13.
 */
@Singleton
public class Master {

    private String name;

    public Master(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
