package cn.steve.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yantinggeng on 2016/5/13.
 */
@Singleton
@Component(modules = MasterModule.class)
public interface MasterComponent {

}
