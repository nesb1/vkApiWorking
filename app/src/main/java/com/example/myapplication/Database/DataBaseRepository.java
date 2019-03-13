package com.example.myapplication.Database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.myapplication.Model.DataBaseModel;
import com.example.myapplication.Model.ResponseVk;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class DataBaseRepository{

    private static final String TAG = "TAG";
    private final Database database;

    public DataBaseRepository(Context context) {
        database = Room.databaseBuilder(context, Database.class,"be33789")
                .build();
    }

    public Completable saveToBd(ResponseVk.VkUser vkUser){
        DataBaseModel dataBaseModel = new DataBaseModel();
        dataBaseModel.firstName = vkUser.getFirstName();
        dataBaseModel.lastName = vkUser.getLastName();
        dataBaseModel.id = 0;
        return Completable
                .fromAction(()->database.dao().insertUser(dataBaseModel));
    }
    public Flowable<List<DataBaseModel>> getUserFromBd(){
        return database
                .dao()
                .getCurrentUser(0L);
    }

}
