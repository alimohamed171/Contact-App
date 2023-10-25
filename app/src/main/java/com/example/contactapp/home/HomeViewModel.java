package com.example.contactapp.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.contactapp.dataBase.MyDataBase;
import com.example.contactapp.models.ModelContact;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<List<ModelContact>> contactList = new MutableLiveData<>();



    public void getContact(){
        MyDataBase.getMyDataBase().getDao()
                .getContact()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ModelContact>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<ModelContact> modelContacts) {
                        contactList.setValue(modelContacts);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void delete(int id){
        MyDataBase.getMyDataBase().getDao()
                .delete(id)
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
