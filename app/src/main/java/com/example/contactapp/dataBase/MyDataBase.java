package com.example.contactapp.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.contactapp.models.ModelContact;

@Database(entities = {ModelContact.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {

    private static MyDataBase myDataBase;

    public abstract MyDao getDao();

    public static void createDataBase(Context context){
        if (myDataBase == null){
            myDataBase = Room.databaseBuilder(context,
                    MyDataBase.class,"contact_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
    }

    public static MyDataBase getMyDataBase(){
        return myDataBase;
    }







}
