package com.example.myapplication.Presentors;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.API.ApiUtils;
import com.example.myapplication.Database.DataBaseRepository;
import com.example.myapplication.Model.DataBaseModel;
import com.example.myapplication.Model.Repository;
import com.example.myapplication.Model.ResponseVk;
import com.example.myapplication.di.AppDeleagate;
import com.example.myapplication.view.MainActivityView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    private static final String TAG = "TAG";
    @Inject
    Repository repository;
    @Inject
    DataBaseRepository dataBaseRepository;

    @Inject
    CompositeDisposable compositeDisposable;


    public MainActivityPresenter() {
        AppDeleagate.getMainComponent().inject(this);
    }



    public void buttonClicked() {
        Disposable responseVkObservable = repository.makeCallToServer(ApiUtils.VK_USER_ID)
                .subscribe(result -> saveDataToLocalDatabaseAndShowResult(result), error -> getDataFromLocal(error));
        compositeDisposable.add(responseVkObservable);
        getViewState().showProgressBar();
        Log.d(TAG, "buttonClicked: ");
    }


    private void showResult(String result) {
        getViewState().showResult(result);
    }

    private void showError(String errorText) {
        Log.d("TAG", "шоу еррор");
        getViewState().hideProgressBar();
        getViewState().showError(errorText);
    }

    private void showToast(String text) {
        getViewState().MakeToast(text);
    }


    private void saveDataToLocalDatabaseAndShowResult(ResponseVk.VkUser vkUser) {
        showResult(vkUser.getFirstName() + " " + vkUser.getLastName());
        getViewState().hideProgressBar();
        Disposable disposable = dataBaseRepository.saveToBd(vkUser)
                .subscribe(() -> Log.d("TAG", "Данные успешно сохранены"), error -> Log.d("TAG", "Произошла ошибка при сохранении данных"));
        compositeDisposable.add(disposable);
    }



    private void getDataFromLocal(Throwable t) {
        Log.d(TAG, "getDataFromLocal: ");
        Disposable disposable = dataBaseRepository.getUserFromBd()
                .subscribe(result -> showResultDatabaseWorking(result.get(0)), error -> showError("Потеряно соединение с сервером"));
        compositeDisposable.add(disposable);
    }


    private void showResultDatabaseWorking(DataBaseModel dataBaseModel) {
        showResult(dataBaseModel.firstName + " " + dataBaseModel.lastName);
        getViewState().hideProgressBar();
        showToast("Потеряно соединение, данные взяты из устройства");
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

}


