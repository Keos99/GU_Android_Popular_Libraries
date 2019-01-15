package ru.geekbrains.android3_1.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import ru.geekbrains.android3_1.R;
import ru.geekbrains.android3_1.mvp.presenter.MainPresenter;
import ru.geekbrains.android3_1.mvp.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements View.OnClickListener, MainView {

    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;

    @InjectPresenter MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOne = findViewById(R.id.btnCounter1);
        buttonTwo = findViewById(R.id.btnCounter2);
        buttonThree = findViewById(R.id.btnCounter3);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
    }

    @ProvidePresenter
    public MainPresenter provideMainPresenter(){
        return new MainPresenter();
    }

    public void onClick(View v) {
        presenter.counterClick(v.getId());
    }

    @Override
    public void setButtonValue(int index, int value) {
        switch (index) {
            case 0:
                buttonOne.setText(String.format(getString(R.string.countEquals), value));
                break;
            case 1:
                buttonTwo.setText(String.format(getString(R.string.countEquals), value));
                break;
            case 2:
                buttonThree.setText(String.format(getString(R.string.countEquals), value));
                break;
        }
    }

    @Override
    public void setButtonOneValue(int value) {

    }
}
