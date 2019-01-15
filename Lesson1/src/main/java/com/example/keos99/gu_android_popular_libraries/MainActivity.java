package com.example.keos99.gu_android_popular_libraries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.keos99.gu_android_popular_libraries.MVP.presenter.Presenter;
import com.example.keos99.gu_android_popular_libraries.MVP.view.MainView;

import java.util.Observable;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private Presenter presenter;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button_counter1);
        button2 = findViewById(R.id.button_counter2);
        button3 = findViewById(R.id.button_counter3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        presenter = new Presenter(this);
    }

    @Override
    public void onClick(View v) {
        presenter.buttonClick(v.getId());
    }

    @Override
    public void setButtonText(int btnIndex, int value) {
        switch (btnIndex){
            case 1:
                button1.setText("Количество = " + value);
                break;
            case 2:
                button2.setText("Количество = " + value);
                break;
            case 3:
                button3.setText("Количество = " + value);
                break;
        }
    }
}
