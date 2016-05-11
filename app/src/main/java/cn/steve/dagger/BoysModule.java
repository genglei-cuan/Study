package cn.steve.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yantinggeng on 2016/5/9.
 */


@Module
public class BoysModule {

    @Named("noname")
    @Provides
    @PerBoy
    Boy provideBoy() {
        return new Boy();
    }

    @Named("name")
    @PerBoy
    @Provides
    Boy provideBoyName() {
        Boy boy = new Boy();
        boy.setName("steve");
        return boy;
    }
}
