package com.example.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.Model.Repository;
import com.example.myapplication.Model.ResponseVk;
import com.example.myapplication.Presentors.MainActivityPresenter;
import com.example.myapplication.di.AppDeleagate;
import com.example.myapplication.di.DataBaseModule;
import com.example.myapplication.di.RepositoryModule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import io.reactivex.Observable;

import static org.mockito.Mockito.when;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class ExampleInstrumentedTest {

    private final FakeAppComponent fakeAppComponent;
    private ResponseVk.VkUser vkUser;

    @Inject
    public ExampleInstrumentedTest(){
        fakeAppComponent = DaggerFakeAppComponent.builder()
                .dataBaseModule(new DataBaseModule())
                .repositoryModule(new RepositoryModule())
                .netModule(new FakeNetModule())
                .build();
        fakeAppComponent.inject(this);
    }

    @Mock
    Repository repository;
    @Inject
    MainActivityPresenter mainActivityPresenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        Context appContext = InstrumentationRegistry.getTargetContext();
        AppDeleagate appDeleagate = (AppDeleagate)appContext.getApplicationContext();
        appDeleagate.setMainComponent(fakeAppComponent);
        vkUser = new ResponseVk.VkUser();
        vkUser.setFirstName("Evgeny");
        vkUser.setLastName("Bulygin");




    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        when(repository.makeCallToServer("kek")).thenReturn(Observable.just(vkUser));
        final ResponseVk.VkUser[] result = new ResponseVk.VkUser[1];


        repository.makeCallToServer("kek").subscribe(res-> result[0]=res);
        Assert.assertEquals(result[0].getFirstName(),"Evgeny");




        /*assertEquals("com.example.myapplication", appContext.getPackageName());*/
    }
}
