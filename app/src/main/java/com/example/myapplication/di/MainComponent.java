package com.example.myapplication.di;

import com.example.myapplication.Model.Repository;
import com.example.myapplication.Presentors.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component (modules = {NetModule.class,RepositoryModule.class,DataBaseModule.class})
public interface MainComponent {
    void inject(MainActivityPresenter mainActivityPresenter);
    void inject(Repository repository);
}


