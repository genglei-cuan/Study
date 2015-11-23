package cn.steve.dagger.school;

import dagger.Module;

/**
 * 由于Dagger规定所有@Provides要放在一个@Module中， 所以我们要么可以在一个Module中用includes参数把其他的Module类包含进来
 * 或者比较建议的是：再创建一个空的Module类，把所有的Module都包含到这个Module中来。
 *
 * Created by yantinggeng on 2015/11/23.
 */

@Module(
    includes = {
        StudentModule.class
    }
)

public class AppModules {

}
