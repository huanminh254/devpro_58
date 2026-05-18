package com.devpro.ard_58_nguyenminhhuan_day05.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.ard_58_nguyenminhhuan_day05.R;
import com.devpro.ard_58_nguyenminhhuan_day05.model.OptionPinterest;

import java.util.List;

public class OptionPinterestAdapter extends RecyclerView.Adapter<OptionPinterestAdapter.OptionTopBarViewHolder> {
    private List<OptionPinterest> list;
    private OnItemClickListener onItemClickListener;
    public OptionPinterestAdapter(List<OptionPinterest> list) {
        this.list = list;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public OptionPinterestAdapter.OptionTopBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OptionTopBarViewHolder optionTopBarViewHolder = new OptionTopBarViewHolder(
                android.view.LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.option_top_bar, parent, false));
        return optionTopBarViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OptionTopBarViewHolder holder, int position) {
        OptionPinterest item = list.get(position);
        holder.textView.setText(item.getName());
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
        if (item.isSelected()) {
            holder.textView.setBackgroundResource(R.drawable.bg_round_option);
            holder.textView.setTextColor(holder.textView.getContext().getResources().getColor(R.color.white));
        }
        else {
            holder.textView.setBackgroundColor(holder.textView.getContext().getResources().getColor(R.color.white));
            holder.textView.setTextColor(holder.textView.getContext().getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
    public class OptionTopBarViewHolder extends RecyclerView.ViewHolder{
        public android.widget.TextView textView;
        public OptionTopBarViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_option);
        }
    }
}
