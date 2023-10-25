package com.example.contactapp.dataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contactapp.models.ModelContact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MyDao {

    @Insert
    Completable insert(ModelContact modelContact);
    @Query("select * from ModelContact")
    Single<List<ModelContact>> getContact();
    @Query("delete from ModelContact where ModelContact.id == :id")
    Completable delete(int id);
    @Update
    Completable update(ModelContact contact);
}
