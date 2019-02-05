package com.example.myapplication.Presentors;

import android.util.Log;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.API.ApiUtils;
import com.example.myapplication.Database.DataBaseRepository;
import com.example.myapplication.Database.Database;
import com.example.myapplication.Database.DatabaseContract;
import com.example.myapplication.Model.ModelContract;
import com.example.myapplication.Model.Repository;
import com.example.myapplication.Model.ResponseVk;
import com.example.myapplication.di.AppDeleagate;
import com.example.myapplication.view.MainActivityView;
import javax.inject.Inject;


@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> implements ModelContract.OnFinishedListener,
        DatabaseContract.OnDatabaseFinishedListener {
    @Inject
    Repository repository;
    @Inject
    DataBaseRepository dataBaseRepository;

    public MainActivityPresenter() {
        AppDeleagate.getMainComponent().inject(this);
    }

    public void buttonClicked() {
        repository.makeCallToServer(ApiUtils.VK_USER_ID, this);
        getViewState().showProgressBar();
    }


    private void showResult(String result) {
        getViewState().showResult(result);
    }/**/

    private void showError(String errorText) {
        getViewState().showError(errorText);
    }

    private void showToast(String text){
        getViewState().MakeToast(text);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onFinishedNetworking(ResponseVk.VkUser vkUser) {
        showResult(vkUser.getFirstName() + " " + vkUser.getLastName());
        getViewState().hideProgressBar();
        dataBaseRepository.saveToBd(vkUser,this);
    }

    @Override
    public void onFailureNetworking(Throwable t) {
        Log.d("TAG", "onFailureNetworking: ");
        dataBaseRepository.getUserFromBd(this,true);
    }

    @Override
    public void onFinishedDatabaseWorking(ResponseVk.VkUser vkUser,Boolean isInternetError) {
        showResult(vkUser.getFirstName() + " " + vkUser.getLastName());
        getViewState().hideProgressBar();
        if (isInternetError) {
            showToast("Потеряно соединение, данные взяты из устройства");
        }
    }

    @Override
    public void onFinishedDatabaseWorking() {

    }

    @Override
    public void onFailureDatabaseWorking(String e) {
        Log.d("TAG", e);
        showToast("Соединение с сервером потеряно");
    }
}
