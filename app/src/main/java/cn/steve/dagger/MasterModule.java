package cn.steve.dagger;

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

    @Provides
    Master provideMaster() {
        return master;
    }

}
