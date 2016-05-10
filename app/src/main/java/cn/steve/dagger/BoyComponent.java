package cn.steve.dagger;

import dagger.Component;

/**
 * Created by Steve on 2016/5/10.
 */

@Component(modules = BoysModule.class)
public interface BoyComponent {

  Boy Boy();
}
