package com.example.myapplication.Model;

import android.util.Log;

import com.example.myapplication.API.ApiUtils;
import com.example.myapplication.API.VkApi;
import com.example.myapplication.di.AppDeleagate;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Repository implements ModelContract {
    private static final String TAG = "TAG";
    @Inject
    Retrofit retrofit;
    @Inject
    VkApi vkApi;
    private Disposable disposable;

    @Inject
    public Repository() {
        AppDeleagate.getMainComponent().inject(this);
    }

    public void makeCallToServer(String userId, final OnFinishedListener onFinishedListener) {
        disposable = vkApi.getUserId(ApiUtils.VK_ACCES_TOKEN, userId, ApiUtils.VK_API_VERSION)
                .map(result -> result.getResponse().get(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onFinishedListener::onFinishedNetworking, onFinishedListener::onFailureNetworking, this::dispose);
    }

    private void dispose() {
        disposable.dispose();
        Log.d(TAG, String.valueOf(disposable.isDisposed()));
    }
}












