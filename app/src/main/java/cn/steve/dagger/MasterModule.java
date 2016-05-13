package cn.steve.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yantinggeng on 2016/5/13.
 */
@Module
public class MasterModule {

    Master master;

    public MasterModule() {
        master = new Master("Panda");
    }

    @Singleton
    @Provides
    Master provideMaster() {
        return master;
    }

}
