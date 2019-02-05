package com.example.myapplication.view;

import com.arellomobile.mvp.MvpView;

public interface MainActivityView extends MvpView {
    void showResult(String text);
    void showError(String text);
    void MakeToast(String text);
    void showProgressBar();
    void hideProgressBar();

}
