package com.example.lesson2hw;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.lesson2hw.mvp.presenter.MainActivityPresenter;
import com.example.lesson2hw.mvp.view.MainActivityView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

    private TextView textView;
    private EditText editText;

    @InjectPresenter
    MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.edittext);
        trackText();
    }

    @Override
    public void textViewSetText(CharSequence text) {
        textView.setText(text);
    }

    public void trackText (){
        RxTextView.textChanges(editText).subscribe(new Observer<CharSequence>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(CharSequence charSequence) {
                mainActivityPresenter.trackTextChanges(charSequence.toString());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

}
