package com.example.myapplication.Database;

import android.arch.persistence.room.RoomDatabase;

import com.example.myapplication.Model.ResponseVk;

@android.arch.persistence.room.Database(entities = {ResponseVk.VkUser.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract Dao dao();

}
