package com.example.myapplication.di;

import com.example.myapplication.Database.DataBaseRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {
    @Provides
    DataBaseRepository provideDataBaseRepository(){
        return new DataBaseRepository(AppDeleagate.getAppContext());
    }
}
