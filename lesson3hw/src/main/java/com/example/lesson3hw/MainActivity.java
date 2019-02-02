package com.example.lesson3hw;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.lesson3hw.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements com.example.lesson3hw.viev.MainActivity {

    @BindView(R.id.buttonConvert)
    Button buttonConvert;

    private MainPresenter presenter;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        presenter = new MainPresenter(this);

        ButterKnife.bind(this);
    }

    @Override
    public Activity getActivity(){
        return activity;
    }

    @OnClick(R.id.buttonConvert)
    public void buttonClick (){
        presenter.buttonClick();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (!(presenter.onRequestPermissionsResult(requestCode, permissions, grantResults))){
            toastAccessDenied();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(resultCode,resultCode,data);
    }

    @Override
    public void toastAccessDenied() {
        Toast.makeText(this,"В доступе отказано", Toast.LENGTH_LONG).show();
    }

    @Override
    public void toastConvertSucsess() {
        Toast.makeText(this,"Сконвертировано", Toast.LENGTH_LONG).show();
    }

    @Override
    public void toastConvertError() {
        Toast.makeText(this,"Ошибка ковертации", Toast.LENGTH_LONG).show();
    }
}
