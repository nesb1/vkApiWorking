package com.example.myapplication;

import com.example.myapplication.di.NetModule;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
class FakeNetModule extends NetModule  {
    @Provides
    NetModule provideFakeNetModule(){
        return mock(NetModule.class);
    }
}
