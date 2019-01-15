package com.example.keos99.gu_android_popular_libraries.MVP.presenter;

import com.example.keos99.gu_android_popular_libraries.MVP.model.Model;
import com.example.keos99.gu_android_popular_libraries.MainActivity;
import com.example.keos99.gu_android_popular_libraries.R;

public class Presenter {
    private Model model;
    private MainActivity mainActivity;

    public Presenter(MainActivity mainActivity){
        model = new Model();
        this.mainActivity = mainActivity;
    }

    public int calcNewModelValue(int elementindex){
        int currentValue = model.getValueAtIndex(elementindex);
        return ++currentValue;
    }

    public void buttonClick(int buttonindex){
        switch (buttonindex){
            case R.id.button_counter1:
                buttonClickHandler(0);
                break;
            case R.id.button_counter2:
                buttonClickHandler(1);
                break;
            case R.id.button_counter3:
                buttonClickHandler(2);
                break;
        }
    }

    public void buttonClickHandler(int index) {
        int newmodelvalue = calcNewModelValue(index);
        model.setValueAtIndex(index, newmodelvalue);
        mainActivity.setButtonText(++index,newmodelvalue);
    }
}