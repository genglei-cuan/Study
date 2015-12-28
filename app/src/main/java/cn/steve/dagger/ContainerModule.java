package cn.steve.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yantinggeng on 2015/12/28.
 */
@Module
public class ContainerModule {

    @Provides
    ShoppingCartModel provideCardModel() {
        return new ShoppingCartModel();
    }

}
