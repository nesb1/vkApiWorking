package com.example.myapplication.Model;


import com.example.myapplication.API.ApiUtils;
import com.example.myapplication.API.VkApi;
import com.example.myapplication.di.AppDeleagate;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class Repository {
    private static final String TAG = "TAG";
    @Inject
    Retrofit retrofit;
    @Inject
    VkApi vkApi;

    @Inject
    public Repository() {
        ((AppDeleagate)AppDeleagate.getAppContext()).getMainComponent().inject(this);
        /*AppDeleagate.getMainComponent().inject(this);*/
    }

    public Observable<ResponseVk.VkUser> makeCallToServer(String userId) {
        return vkApi.getUserId(ApiUtils.VK_ACCES_TOKEN, userId, ApiUtils.VK_API_VERSION)
                .map(result -> result.getResponse().get(0));
    }
}












