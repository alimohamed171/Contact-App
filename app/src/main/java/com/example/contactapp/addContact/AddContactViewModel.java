package com.example.contactapp.addContact;

import androidx.lifecycle.ViewModel;

import com.example.contactapp.dataBase.MyDataBase;
import com.example.contactapp.models.ModelContact;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddContactViewModel extends ViewModel {

    SuccessMessage successMessage;

    public void setTestString(SuccessMessage successMessage) {
        this.successMessage = successMessage;
    }



    //public String test ="fail";


    public void addContact(ModelContact modelContact){
        MyDataBase.getMyDataBase()
                .getDao().insert(modelContact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                      successMessage.onClickRoot("Success" );
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }







}
