package com.example.myapplication.Database;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.myapplication.Model.ResponseVk;

import io.reactivex.Flowable;
@android.arch.persistence.room.Dao
public interface Dao {
    @Insert
    void insertUser(ResponseVk.VkUser vkUser);
    @Query("SELECT * FROM vkuser")
    Flowable<ResponseVk.VkUser> getAllPeople();

    @Query("SELECT * FROM vkUser WHERE id = :id")
    Flowable<ResponseVk.VkUser> getUser(long id);
}
