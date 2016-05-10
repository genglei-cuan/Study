package cn.steve.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yantinggeng on 2016/5/9.
 */


@Module
public class BoysModule {

  @Provides
  @PerBoy
  Boy provideBoy() {
    return new Boy();
  }
}
