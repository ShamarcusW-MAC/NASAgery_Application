package com.example.nasagery_application.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nasagery_application.R;
import com.example.nasagery_application.model.Item;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<Item> images;
    private final int limit = 20;

    public ImageAdapter(Context context, List<Item> images){

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
                .load(images.get(position).getLinks().get(0).getHref())
                .into(holder.nasaImageView);


        holder.imageTitleTextView.setText(images.get(position).getData().get(0).getTitle());
        holder.imageAuthorTextView.setText(images.get(position).getData().get(0).getSecondaryCreator());
        holder.imageDateTextView.setText(images.get(position).getData().get(0).getDateCreated());
        holder.imageDescriptionTextView.setText(images.get(position).getData().get(0).getDescription());

        Log.d("Link", images.get(position).getLinks().get(0).getHref());

    }

    @Override
    public int getItemCount() {
        if(images != null && images.size() > limit) {
            return limit;
        }else if (images != null && images.size() < limit){
            return images.size();
        } else {
            return 0;
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView nasaImageView;
        TextView imageTitleTextView;
        TextView imageAuthorTextView;
        TextView imageDateTextView;
        TextView imageDescriptionTextView;

        ImageViewHolder(View itemView){
            super(itemView);
            nasaImageView = itemView.findViewById(R.id.nasaimage_imageview);
            imageTitleTextView = itemView.findViewById(R.id.imagetitle_textview);
            imageAuthorTextView = itemView.findViewById(R.id.image_author_textview);
            imageDateTextView = itemView.findViewById(R.id.image_date_textview);

            imageDescriptionTextView = itemView.findViewById(R.id.image_description_textview);


        }
    }

}
