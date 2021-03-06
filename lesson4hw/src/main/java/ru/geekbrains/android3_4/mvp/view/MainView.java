package ru.geekbrains.android3_4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
    void showMessage(String text);
    void setImageUrl(String url);
    void setUsernametext(String text);
    void showLoading();
    void init();
    void showAvatar(String avatarUrl);
    void setUsername(String username);
    void hideLoading();
    void updateRepoList();
    void showError(String message);
}
