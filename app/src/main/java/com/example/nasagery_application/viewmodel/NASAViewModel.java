package com.example.nasagery_application.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.nasagery_application.model.Collection;
import com.example.nasagery_application.model.Item;
import com.example.nasagery_application.network.NASAFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NASAViewModel extends ViewModel {

    private NASAFactory nasaFactory = new NASAFactory();

    public Observable<Collection> getImage(String url) {
        return nasaFactory.getImage(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }



}
