package com.example.contactapp;

import android.app.Application;

import com.example.contactapp.dataBase.MyDataBase;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyDataBase.createDataBase(this);
    }
}
