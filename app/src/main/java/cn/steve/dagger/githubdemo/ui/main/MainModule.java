
package cn.steve.dagger.githubdemo.ui.main;


import javax.inject.Singleton;

import cn.steve.dagger.githubdemo.AppModule;
import cn.steve.dagger.githubdemo.interactors.FindItemsInteractor;
import dagger.Module;
import dagger.Provides;

@Module(
    injects = MainActivity.class,
    addsTo = AppModule.class
)
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public MainView provideView() {
        return view;
    }

    @Provides
    @Singleton
    public MainPresenter providePresenter(MainView mainView,
                                          FindItemsInteractor findItemsInteractor) {
        return new MainPresenterImpl(mainView, findItemsInteractor);
    }
}
