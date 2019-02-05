package com.example.myapplication.Database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.example.myapplication.Model.ModelContract;
import com.example.myapplication.Model.ResponseVk;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataBaseRepository implements DatabaseContract {

    private static final String TAG = "TAG";
    private final Database database;
    private Disposable disposable;

    public DataBaseRepository(Context context) {
        database = Room.databaseBuilder(context, Database.class,"bd4")
                .build();
    }

    public void saveToBd(ResponseVk.VkUser vkUser, OnDatabaseFinishedListener onDatabaseFinishedListner){
        ResponseVk.VkUser vkUser1 = new ResponseVk.VkUser();
        vkUser1.setLastName(vkUser.getLastName());
        vkUser1.setFirstName(vkUser.getFirstName());
        vkUser1.setId(1);
        Completable.fromAction(()->database
        .dao()
        .insertUser(vkUser1))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->onDatabaseFinishedListner.onFinishedDatabaseWorking(),error-> Log.d(TAG, "saveToBd: error"));

    }
    public void getUserFromBd(OnDatabaseFinishedListener onDatabaseFinishedListner, boolean isNetworkError){
        disposable = database
                .dao()
                .getUser(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vkUser -> onDatabaseFinishedListner.onFinishedDatabaseWorking(vkUser,isNetworkError),
                        error->onDatabaseFinishedListner.onFailureDatabaseWorking(error.toString()));

    }

}
