package com.example.nasagery_application.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nasagery_application.R;
import com.example.nasagery_application.adapter.ImageAdapter;
import com.example.nasagery_application.model.Collection;
import com.example.nasagery_application.model.Datum;
import com.example.nasagery_application.model.Image;
import com.example.nasagery_application.model.Item;
import com.example.nasagery_application.model.Link;
import com.example.nasagery_application.network.NASAFactory;
import com.example.nasagery_application.network.NASAService;
import com.example.nasagery_application.viewmodel.NASAViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private NASAViewModel nasaViewModel;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchEditText = findViewById(R.id.search_edittext);

        Button searchButton = findViewById(R.id.search_button);

        nasaViewModel = ViewModelProviders.of(this).get(NASAViewModel.class);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compositeDisposable.add(nasaViewModel.getImage(searchEditText.getText().toString())
                        .subscribe(images -> {

                            {

                                displayImages(images.getCollection().getItems().get(0).getLinks());
                            }

                        }));

            }
        });

    }

    private void displayImages(List<Link> images){
        ImageAdapter imageAdapter = new ImageAdapter(this, images);
        RecyclerView recyclerView = findViewById(R.id.image_recyclerview);
        recyclerView.setAdapter(imageAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
