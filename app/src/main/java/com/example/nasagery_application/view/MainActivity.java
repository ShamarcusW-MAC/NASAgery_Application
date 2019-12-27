package com.example.nasagery_application.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nasagery_application.R;
import com.example.nasagery_application.adapter.ImageAdapter;
import com.example.nasagery_application.databinding.ActivityMainBinding;
import com.example.nasagery_application.model.Image;
import com.example.nasagery_application.model.Item;
import com.example.nasagery_application.viewmodel.NASAViewModel;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private NASAViewModel nasaViewModel;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private int add = 20;
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        nasaViewModel = ViewModelProviders.of(this).get(NASAViewModel.class);
        activityMainBinding.setViewModel(nasaViewModel);

        activityMainBinding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nasaViewModel.makeCall(activityMainBinding.searchEdittext.getText().toString());

            }
        });

        activityMainBinding.getViewModel().image.observe(this, new Observer<Image>() {
            @Override
            public void onChanged(Image image) {

                displayImages(image.getCollection().getItems());

            }
        });



//      Initiates when the user pulls to refresh the recycler view.
//      Once initiated, 20 more photos will be added to the view.
        activityMainBinding.swipeRecyclerview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                compositeDisposable.add(nasaViewModel.getImage(activityMainBinding.searchEdittext.getText().toString())
                        .subscribe(images -> {

                            {
                                displayImages(images.getCollection().getItems());
                                imageAdapter.notifyDataSetChanged();
                                imageAdapter.limit = add;
                                add += 20;
                                activityMainBinding.swipeRecyclerview.setRefreshing(false);
                                Toast.makeText(MainActivity.this, "Number of photos: " + imageAdapter.getItemCount(), Toast.LENGTH_SHORT).show();
                            }

                        }, throwable -> {
                            Log.d("TAG_ERROR", throwable.getMessage());
                        }

                        )

                );
            }
        });

    }

    //Adapter is initialized and recycler view is filled with items
    private void displayImages(List<Item> images){
        imageAdapter = new ImageAdapter(this, images);
        recyclerView = findViewById(R.id.image_recyclerview);

        if(images.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            activityMainBinding.noresultsTextview.setVisibility(View.VISIBLE);

        }else {
            activityMainBinding.noresultsTextview.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(imageAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

        }

    }

}
