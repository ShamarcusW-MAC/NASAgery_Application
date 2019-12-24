package com.example.nasagery_application.network;

import com.example.nasagery_application.model.Collection;
import com.example.nasagery_application.model.Image;
import com.example.nasagery_application.model.Item;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NASAService {


    @GET("/search?media_type=image&q")
    Observable<Image> getImage(@Query("q")String keyword);

}
