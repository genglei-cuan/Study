package cn.steve.dagger;

import android.app.Application;

import cn.steve.dagger.domain.DomainModule;
import cn.steve.dagger.interactors.InteractorsModule;
import dagger.Module;
import dagger.Provides;

@Module(
    injects = {
        App.class
    },
    includes = {
        DomainModule.class,
        InteractorsModule.class
    }
)
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public Application provideApplication() {
        return app;
    }
}
