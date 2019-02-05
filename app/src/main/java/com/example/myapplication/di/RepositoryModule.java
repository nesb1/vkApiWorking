package com.example.myapplication.di;

import com.example.myapplication.Model.Repository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    Repository provideRepository(){
        return new Repository();
    }
}
