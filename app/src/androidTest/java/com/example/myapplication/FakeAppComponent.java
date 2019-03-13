package com.example.myapplication;

import com.example.myapplication.di.DataBaseModule;
import com.example.myapplication.di.MainComponent;
import com.example.myapplication.di.NetModule;
import com.example.myapplication.di.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {NetModule.class, RepositoryModule.class, DataBaseModule.class})
public interface FakeAppComponent extends MainComponent {
    void inject(ExampleInstrumentedTest exampleInstrumentedTest);
}
