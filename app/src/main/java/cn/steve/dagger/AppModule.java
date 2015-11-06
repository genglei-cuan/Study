package cn.steve.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yantinggeng on 2015/11/6.
 */

// @Module：把这个类标识为Dagger module。
// injects：标识module将要注入这个类的任何依赖。
// 我们需要明确指定将直接注入到对象中的那些类.
@Module(
    injects = {
        App.class
    }
)
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }


    //@Providers：标识函数作为注入提供者， 函数名并不重要，它只依赖于所提供的类类型。
    //@Singleton：如果标识为Singleton，那这个函数会一直返回相同的对象实例，
    // 这比常规的单例好很多。 否则，每次注入类型都会得到一个新的实例。
    // 在这个例子中，由于我们没有创建新实例，而是返回已经存在的实例，
    // 因此即使不把函数标识为Singleton，每次调用还是会返回相同的实例的，
    // 但这样能够更好地说明提供者到底做了什么。
    // Application实例是唯一的。
    @Provides
    @Singleton
    public Context provideApplication() {
        return app;//返回同一个实例
    }


}
