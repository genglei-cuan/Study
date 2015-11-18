
package cn.steve.dagger.githubdemo.ui.main;


import java.util.List;

import cn.steve.dagger.githubdemo.interactors.FindItemsInteractor;

public class MainPresenterImpl implements MainPresenter, OnFinishedListener {

    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    public MainPresenterImpl(MainView mainView, FindItemsInteractor findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }

    @Override
    public void onResume() {
        mainView.showProgress();
        findItemsInteractor.findItems(this);
    }

    @Override
    public void onItemClicked(int position) {
        mainView.showMessage(String.format("Position %d clicked", position + 1));
    }

    @Override
    public void onFinished(List<String> items) {
        mainView.setItems(items);
        mainView.hideProgress();
    }
}
