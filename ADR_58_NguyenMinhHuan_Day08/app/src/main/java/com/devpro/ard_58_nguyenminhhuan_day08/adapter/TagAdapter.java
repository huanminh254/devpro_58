package com.devpro.ard_58_nguyenminhhuan_day08.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.ard_58_nguyenminhhuan_day08.R;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Tag;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {
    private List<Tag> tags = new ArrayList<>();
    private int selectedPosition = -1;
    private OnItemTagClickListener onItemTagClickListener;
    public interface OnItemTagClickListener {
        void onItemTagClick(Tag tag);
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_tag, parent, false);
        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        Tag tag = tags.get(position);
        holder.nameTag.setText(tag.getName());
        if(tag.isSelected()){
            holder.nameTag.setTextColor(Color.WHITE);
            holder.nameTag.setBackgroundResource(R.drawable.bg_option_tag);
        }else{
            holder.nameTag.setTextColor(Color.BLACK);
            holder.nameTag.setBackgroundResource(R.drawable.bg_option_tag_white);
        }

    }
    public Tag getSelectedTag() {
        if (selectedPosition != -1 && selectedPosition < tags.size()) {
            return tags.get(selectedPosition);
        }
        return null;
    }
    @Override
    public int getItemCount() {
        return tags != null ? tags.size() : 0;
    }

    public class TagViewHolder extends RecyclerView.ViewHolder {
        TextView nameTag;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTag = itemView.findViewById(R.id.tv_option_item_tag);
            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (position != selectedPosition) {
                    if (selectedPosition != -1) {
                        tags.get(selectedPosition).setSelected(false);
                        notifyItemChanged(selectedPosition);
                    }
                    tags.get(position).setSelected(true);
                    notifyItemChanged(position);
                    selectedPosition = position;
                    if (onItemTagClickListener != null) {
                        onItemTagClickListener.onItemTagClick(tags.get(position));
                    }
                }else{
                    tags.get(position).setSelected(false);
                    notifyItemChanged(position);
                    selectedPosition = -1;
                }
            });
        }
    }
}
