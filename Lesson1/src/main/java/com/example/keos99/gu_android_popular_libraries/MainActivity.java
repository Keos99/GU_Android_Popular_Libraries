package com.example.keos99.gu_android_popular_libraries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.keos99.gu_android_popular_libraries.MVP.Model;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer, View.OnClickListener {

    private Model model;
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

        model = new Model();
        model.addObserver(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_counter1:
                model.setValueAtIndex(0);
                break;
            case R.id.button_counter2:
                model.setValueAtIndex(1);
                break;
            case R.id.button_counter3:
                model.setValueAtIndex(2);
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        button1.setText("Количество = " + model.getValueAtIndex(0));
        button2.setText("Количество = " + model.getValueAtIndex(1));
        button3.setText("Количество = " + model.getValueAtIndex(2));
    }
}
