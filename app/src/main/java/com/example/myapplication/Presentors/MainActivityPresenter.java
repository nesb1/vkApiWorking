package com.example.myapplication.Presentors;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.Database.DataBaseRepository;
import com.example.myapplication.Model.DataBaseModel;
import com.example.myapplication.Model.Repository;
import com.example.myapplication.Model.ResponseVk;
import com.example.myapplication.Utils.IdCorrect;
import com.example.myapplication.di.AppDeleagate;
import com.example.myapplication.view.MainActivityView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    private static final String TAG = "TAG";
    @Inject
    Repository repository;
    @Inject
    DataBaseRepository dataBaseRepository;

    @Inject
    CompositeDisposable compositeDisposable;

        @Inject
    public MainActivityPresenter() {
        ((AppDeleagate)AppDeleagate.getAppContext()).getMainComponent().inject(this);
    }



    public void buttonClicked(String str) {
        IdCorrect idCorrect = new IdCorrect();
        if (idCorrect.isCorrectId(str)) TakeServerRequest(str);
    }



    private void TakeServerRequest(String userId) {
        Disposable responseVkObservable = repository.makeCallToServer(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d("TAG", "Данные успешно сохранены"), error -> Log.d("TAG", "Произошла ошибка при сохранении данных"));
        compositeDisposable.add(disposable);
    }



    private void getDataFromLocal(Throwable t) {
        Log.d(TAG, "getDataFromLocal: ");
        Disposable disposable = dataBaseRepository.getUserFromBd()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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


