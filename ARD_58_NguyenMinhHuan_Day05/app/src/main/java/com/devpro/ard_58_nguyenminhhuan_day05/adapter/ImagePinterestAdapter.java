package com.devpro.ard_58_nguyenminhhuan_day05.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.ard_58_nguyenminhhuan_day05.R;
import com.devpro.ard_58_nguyenminhhuan_day05.model.ImagePinterest;

import java.util.List;

public class ImagePinterestAdapter extends RecyclerView.Adapter<ImagePinterestAdapter.PinterestViewHolder> {
    private List<ImagePinterest> imagePinterestList;
    public ImagePinterestAdapter(List<ImagePinterest> imagePinterestList) {
        this.imagePinterestList = imagePinterestList;
    }

    @NonNull
    @Override
    public PinterestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PinterestViewHolder pinterestViewHolder = new PinterestViewHolder(
                android.view.LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.image_pinterest, parent, false));
        return pinterestViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PinterestViewHolder holder, int position) {
        ImagePinterest item = imagePinterestList.get(position);
        holder.imageView.setImageResource(item.getUrl());
    }

    @Override
    public int getItemCount() {
        return imagePinterestList != null ? imagePinterestList.size() : 0;
    }

    public class PinterestViewHolder extends RecyclerView.ViewHolder {
        public android.widget.ImageView imageView;
        public PinterestViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_pinterest);
        }

    }
}
