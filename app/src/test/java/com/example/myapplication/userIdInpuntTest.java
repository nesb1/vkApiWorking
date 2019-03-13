package com.example.myapplication;

import com.example.myapplication.Presentors.MainActivityPresenter;
import com.example.myapplication.Utils.IdCorrect;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class userIdInpuntTest {

    private MainActivityPresenter mainActivityPresenter;
    private IdCorrect idCorrect;

    @Before
    public void setUp(){
        idCorrect = new IdCorrect();

    }
    @Test
    public void Test_TruePassWord(){
        boolean result = idCorrect.isCorrectId("124205288");
        Assert.assertEquals(result,true);
    }

    @Test
    public void Test_That_Text_In_Id_uncorrect(){
        boolean result = idCorrect.isCorrectId("kek");
        Assert.assertEquals(result,false);
    }

    @Test
    public void Test_That_Null_In_Id_uncorrect(){
        boolean result = idCorrect.isCorrectId(null);
        Assert.assertEquals(result,false);
    }
}
