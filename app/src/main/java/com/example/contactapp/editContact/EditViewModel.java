package com.example.contactapp.editContact;

import androidx.lifecycle.ViewModel;

import com.example.contactapp.dataBase.MyDataBase;
import com.example.contactapp.models.ModelContact;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EditViewModel extends ViewModel {

    public void editContact(ModelContact contact){
        MyDataBase.getMyDataBase().getDao()
                .update(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }




}
