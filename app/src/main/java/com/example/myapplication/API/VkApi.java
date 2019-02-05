package com.example.myapplication.API;

import com.example.myapplication.Model.ResponseVk;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface VkApi {
    @POST("users.get")
    Observable<ResponseVk> getUserId(@Query("access_token") String key, @Query("user_id")String userId, @Query("v") String v);
}
