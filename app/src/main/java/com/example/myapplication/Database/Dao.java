package com.example.myapplication.Database;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.myapplication.Model.DataBaseModel;

import java.util.List;

import io.reactivex.Flowable;
@android.arch.persistence.room.Dao
public interface Dao {
    @Insert
    void insertUser(DataBaseModel dataBaseModel);

    @Query("SELECT * FROM databasemodel WHERE id= :id")
    Flowable<List<DataBaseModel>> getCurrentUser(long id);

    @Query("SELECT * FROM databasemodel")
    Flowable<List<DataBaseModel>> getUser();
}
