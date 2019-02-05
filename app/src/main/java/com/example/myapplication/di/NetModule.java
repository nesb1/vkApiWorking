package com.example.myapplication.di;

import com.example.myapplication.API.ApiUtils;
import com.example.myapplication.API.VkApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiUtils.VK_BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    @Singleton
    @Provides
    HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.readTimeout(4, TimeUnit.SECONDS);
        okhttpClientBuilder.addInterceptor(interceptor);
        return okhttpClientBuilder.build();
    }
    @Singleton
    @Provides
    VkApi provideVkApi(Retrofit retrofit){
        VkApi vkApi = retrofit.create(VkApi.class);
        return vkApi;
    }
}
