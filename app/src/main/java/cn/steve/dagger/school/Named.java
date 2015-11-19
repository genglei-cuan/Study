package cn.steve.dagger.school;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 对于单纯的类型，有时候并不能满足指定的依赖的需求， 需要自定义新的注解 Created by yantinggeng on 2015/11/19.
 */
//添加限定符注释. 这种注释本身有一个@Qualifier注释

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Named {

    String value() default "";
}
//这样就有了一个新的注释@Named，来帮助我们限定我们想提供的类对象，还有我们获得的类对象的实例
