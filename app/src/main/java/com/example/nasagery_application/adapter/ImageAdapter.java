package com.example.nasagery_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nasagery_application.R;
import com.example.nasagery_application.model.Item;
import com.example.nasagery_application.model.Link;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<Link> images;

    public ImageAdapter(Context context, List<Link> images){

        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item_layout, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(images.get(position).getHref())
                .into(holder.nasaImageView);
    }

    @Override
    public int getItemCount() {
        if(images != null) {
            return images.size();
        }else{
            return 0;
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView nasaImageView;

        ImageViewHolder(View itemView){
            super(itemView);
            nasaImageView = itemView.findViewById(R.id.nasaimage_imageview);


        }
    }

}
