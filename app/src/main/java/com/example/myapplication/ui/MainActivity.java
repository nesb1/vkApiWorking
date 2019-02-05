package com.example.myapplication.ui;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.myapplication.Database.DataBaseRepository;
import com.example.myapplication.Presentors.MainActivityPresenter;
import com.example.myapplication.R;
import com.example.myapplication.view.MainActivityView;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {
    @InjectPresenter
    MainActivityPresenter mainActivityPresenter;
    private TextView textView;
    private Button button;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.tv);
        button = findViewById(R.id.button);
        /*https://api.vk.com/method/users.get?user_id=124205288&v=5.92&access_token=51d3eced51d3eced51d3eced0d51bb81c1551d351d3eced0d868aad2dd1091212c1eb42*/
        button.setOnClickListener(v -> mainActivityPresenter.buttonClicked());


    }

    @Override
    public void showResult(String text) {
        textView.setText(text);
    }

    @Override
    public void showError(String text) {
        textView.setText(text);
    }

    @Override
    public void MakeToast(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        textView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        textView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
