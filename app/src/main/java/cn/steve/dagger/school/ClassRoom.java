package cn.steve.dagger.school;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

/**
 * Created by yantinggeng on 2015/11/19.
 */
public class ClassRoom {

    //加上@Inject，表示当前类要注入一个Student的对象
    //思考：既然不是通过反射来构造对象的，是通过预编译的，那么他是怎么知道谁来提供这个对象呢？
    @Inject
    Teacher teacher;

    //懒加载，等到调用的时候才进行注入
    @Inject
    Lazy<Student> lazyStudent;
    //需要多个对象实例的，而不是仅仅注入一个对象实例,每次调用provider的get函数的时候，都将返回新的对象实例
    @Inject
    Provider<Student> providerStudent;

    //通过自定义的注解限制注入的对象的性质
    @Inject
    @Named("steveyan")
    Student steveyan;
    @Inject
    @Named("tinggengyan")
    Student tinggengyan;


    public void study() {
        lazyStudent.get();//这样就能得到一个Student对象
    }

    public void studyProvider() {
        providerStudent.get();//得到对象1
        providerStudent.get();//得到对象2
        //两个对象是不同的对象
    }

}
