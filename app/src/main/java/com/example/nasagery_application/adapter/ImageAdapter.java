package com.example.nasagery_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.nasagery_application.R;
import com.example.nasagery_application.databinding.ImageItemLayoutBinding;
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ImageItemLayoutBinding binding = ImageItemLayoutBinding.inflate(layoutInflater, parent, false);

        return new ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {


        Item item = images.get(position);

        holder.bind(item);
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
        ImageItemLayoutBinding binding;

        public ImageViewHolder(ImageItemLayoutBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(Item item)
        {
            binding.setData(item.getData().get(0));
            binding.setLink(item.getLinks().get(0));
            nasaImageView = itemView.findViewById(R.id.nasaimage_imageview);
            Glide.with(itemView.getContext())
                    .load(item.getLinks().get(0).getHref())
                .into(nasaImageView);
            binding.executePendingBindings();
        }
    }

}
