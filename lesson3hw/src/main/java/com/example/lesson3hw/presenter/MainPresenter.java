package com.example.lesson3hw.presenter;

import android.content.Intent;

import com.example.lesson3hw.model.MainModel;
import com.example.lesson3hw.viev.MainActivity;

public class MainPresenter {

    private MainActivity viev;
    private MainModel model;

    public MainPresenter (MainActivity viev) {
        this.viev = viev;
        model = new MainModel(viev);
    }

    public void buttonClick (){
        model.takeImage();
    }

    public boolean onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        return  model.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        model.onActivityResult(requestCode, resultCode, data);
    }

}
