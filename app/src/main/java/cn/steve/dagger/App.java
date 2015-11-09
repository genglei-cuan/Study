
package cn.steve.dagger;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import cn.steve.dagger.domain.AnalyticsManager;
import dagger.ObjectGraph;

/**
 * demo来源于 ：https://github.com/antoniolg/DaggerExample
 *
 * 但是目前不知道为何不能运行。。。。。还不熟悉
 */

public class App extends Application {

    @Inject
    AnalyticsManager analyticsManager;
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
        analyticsManager.registerAppEnter();
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
    }
}
