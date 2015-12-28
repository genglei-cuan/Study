package cn.steve.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Steve on 2015/12/27.
 */

@Module
public class ActivityModule {

    @Provides
    UserModel provideUserModel() {
        return new UserModel("Steve", "male");
    }

}
