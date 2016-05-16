package cn.steve.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 现在的需求是想创建一个全局的对象，不考虑Android的特殊性，
 *
 *
 * Created by yantinggeng on 2016/5/13.
 */
@Singleton
@Component(modules = MasterModule.class)
public interface MasterComponent {

  @Singleton
  Master master();

}
