package com.example.lesson2hw.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.lesson2hw.mvp.view.MainActivityView;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

    public void trackTextChanges(String text){
        getViewState().textViewSetText(text);
    }
}
