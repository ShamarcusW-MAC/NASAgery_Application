package com.example.nasagery_application.viewmodel;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.nasagery_application.model.Image;
import com.example.nasagery_application.network.NASAFactory;
import com.example.nasagery_application.view.MainActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NASAViewModel extends ViewModel {

    public MainActivity mainActivity;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<Image> image = new MutableLiveData();



    private NASAFactory nasaFactory = new NASAFactory();


    public Observable<Image> getImage(String url) {
        return nasaFactory.getImage(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void makeCall(String editText) {
        compositeDisposable.add(getImage(""+editText)
                .subscribe(images -> {

                    {
                        image.postValue(images);
                    }

                }, throwable -> {
                    Log.d("TAG_ERROR", throwable.getMessage());
                }));
    }

}
