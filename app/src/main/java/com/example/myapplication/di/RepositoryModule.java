package com.example.myapplication.di;

import com.example.myapplication.Model.Repository;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class RepositoryModule {
    @Provides
    Repository provideRepository(){
        return new Repository();
    }
    @Provides
    CompositeDisposable provideCompositeDisposible(){
        return new CompositeDisposable();
    }
}
