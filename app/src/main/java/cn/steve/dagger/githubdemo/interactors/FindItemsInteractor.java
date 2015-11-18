package cn.steve.dagger.githubdemo.interactors;

import cn.steve.dagger.githubdemo.ui.main.OnFinishedListener;

public interface FindItemsInteractor {

    public void findItems(OnFinishedListener listener);
}
