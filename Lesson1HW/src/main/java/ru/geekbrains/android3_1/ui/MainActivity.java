package ru.geekbrains.android3_1.ui;

import android.os.Bundle;

import android.widget.Button;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import ru.geekbrains.android3_1.R;
import ru.geekbrains.android3_1.mvp.presenter.MainPresenter;
import ru.geekbrains.android3_1.mvp.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    String count;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOne = findViewById(R.id.btnCounter1);
        buttonTwo = findViewById(R.id.btnCounter2);
        buttonThree = findViewById(R.id.btnCounter3);

        buttonOne.setOnClickListener(view -> presenter.buttonOneClick());
        buttonTwo.setOnClickListener(view -> presenter.buttonTwoClick());
        buttonThree.setOnClickListener(view -> presenter.buttonThreeClick());
    }

    @Override
    public void setButtonOneValue(int value) {
        buttonOne.setText(getString(R.string.count) + " " + value);
    }

    @Override
    public void setButtonTwoValue(int value) {
        buttonTwo.setText(getString(R.string.count) + " " + value);
    }

    @Override
    public void setButtonThreeValue(int value) {
        buttonThree.setText(getString(R.string.count) + " " + value);
    }
}
