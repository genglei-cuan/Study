package cn.steve.dagger.school;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yantinggeng on 2015/11/19.
 */

//@Provides必须出现在@Module标注的类中，主要函数中出现了@Provides注解，就必须要在类上加上@Module注解
@Module(
    //注入对象列表绑定
    injects = ClassRoom.class,
    //可能被除了injects绑定的列表意外的类使用，可以声明为true
    library = true
)
public class StudentModule {

    // @Provides标明了，由这个函数来提供Student对象
    @Provides
    Student provideStudent() {
        return new Student();
    }

    // @Singleton注明了这个是个单例的对象
    // 需要注意的是@Singleton 注释对Dagger有效， 也只在一个ObjectGraph中生效。 若是有多个ObjectGraph， 则有多个相应的@Singleton对象。
    @Provides
    @Singleton
    Teacher provideTeacher() {
        return new Teacher();
    }

    //对外提供有自定义限制的对象
    @Provides
    @Named("steveyan")
    Student provideSteveyanStudent() {
        return new Student();
    }

    @Provides
    @Named("tinggengyan")
    Student provideTinggengyanStudent() {
        return new Student();
    }


}
