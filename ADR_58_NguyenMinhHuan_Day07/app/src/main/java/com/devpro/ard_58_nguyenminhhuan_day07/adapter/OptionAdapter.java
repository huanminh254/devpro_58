package com.devpro.ard_58_nguyenminhhuan_day07.adapter;

import static java.security.AccessController.getContext;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.ard_58_nguyenminhhuan_day07.R;
import com.devpro.ard_58_nguyenminhhuan_day07.model.Option;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {
    List<Option> options;
    private int indexSelected = 0;
    public OptionAdapter(List<Option> options) {
        this.options = options;
    }

    @NonNull
    @Override
    public OptionAdapter.OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OptionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.option_navbar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OptionAdapter.OptionViewHolder holder, int position) {
        Option option = options.get(position);
        holder.tvOption.setText(option.getName());
        if(option.isSelected()){
            holder.tvOption.setBackgroundResource(R.drawable.bg_round_black);
            holder.tvOption.setTextColor(Color.WHITE);
        }else{
            holder.tvOption.setBackgroundResource(R.drawable.bg_round_raidius);
            holder.tvOption.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder {
        TextView tvOption;
        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOption = itemView.findViewById(R.id.tv_option);
            tvOption.setOnClickListener(v->{
                int position = getBindingAdapterPosition();
                options.get(position).setSelected(true);
                options.get(indexSelected).setSelected(false);
                notifyItemChanged(position);
                notifyItemChanged(indexSelected);
                indexSelected = position;
            });
        }
    }
}