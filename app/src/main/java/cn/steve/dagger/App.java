package cn.steve.dagger;

import android.app.Application;

import java.util.List;

import dagger.ObjectGraph;

/**
 * ObjectGraph创建和直接注入的组合方式在App类中可以看到。 主对象图在Application类中创建并被注入以获得依赖。
 *
 * Created by yantinggeng on 2015/11/6.
 */
public class App extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create();
        objectGraph.inject(this);

    }

    private List<Object> getModules() {
        return null;
    }


}
