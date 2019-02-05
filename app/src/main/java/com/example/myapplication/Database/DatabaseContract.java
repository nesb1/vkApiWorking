package com.example.myapplication.Database;

import com.example.myapplication.Model.ResponseVk;

public interface DatabaseContract {
    interface OnDatabaseFinishedListener{
        void onFinishedDatabaseWorking(ResponseVk.VkUser vkUser, Boolean isError);
        void onFinishedDatabaseWorking();
        void onFailureDatabaseWorking(String e);
    }
}
