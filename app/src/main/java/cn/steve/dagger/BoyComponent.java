package cn.steve.dagger;

import javax.inject.Named;

import dagger.Component;

/**
 * Created by Steve on 2016/5/10.
 */

@Component(modules = BoysModule.class)
@PerBoy
public interface BoyComponent {

    //依赖于boys的component需要这个对象，这样做的目标是向外暴露，缺少就会报错
    //所以说，component模块化之后，需要被依赖的部分必须显性的声明，表示提供给上层依赖

    @Named("name")
    Boy boyName();

    @Named("noname")
    Boy boy();
}
