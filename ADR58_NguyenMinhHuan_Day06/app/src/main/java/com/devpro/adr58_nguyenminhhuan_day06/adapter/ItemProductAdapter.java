package com.devpro.adr58_nguyenminhhuan_day06.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.adr58_nguyenminhhuan_day06.R;
import com.devpro.adr58_nguyenminhhuan_day06.model.ItemProduct;

import java.util.List;

public class ItemProductAdapter extends RecyclerView.Adapter<ItemProductAdapter.ViewHolder>{
    private List<ItemProduct> itemProductList;
    private OnItemClickListener listener;
    private int layoutId;

    public ItemProductAdapter(List<ItemProduct> itemProductList, int layoutId, OnItemClickListener listener) {
        this.itemProductList = itemProductList;
        this.layoutId = layoutId;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemProductAdapter.ViewHolder holder, int position) {
        ItemProduct itemProduct = itemProductList.get(position);
        holder.name.setText(itemProduct.getName());
        holder.price.setText("$" + itemProduct.getPrice());
        holder.image.setImageResource(itemProduct.getImage());
        holder.quantity.setText(String.valueOf(itemProduct.getQuantity()));
        if (holder.heart != null) {
            holder.heart.setChecked(itemProduct.isCheck());
        }
    }

    @Override
    public int getItemCount() {
        return itemProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView price;
        private TextView quantity;
        private CheckBox heart;
        private ImageButton btn_cong;
        private ImageButton btn_tru;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name_item_product);
            price = itemView.findViewById(R.id.tv_price_item_product);
            image = itemView.findViewById(R.id.image_item_product);
            quantity = itemView.findViewById(R.id.tv_quantity_item_product);
            heart = itemView.findViewById(R.id.heart_item_product);
            btn_cong = itemView.findViewById(R.id.btn_cong);
            btn_tru = itemView.findViewById(R.id.btn_tru);

            btn_cong.setOnClickListener(v->{
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ItemProduct itemProduct = itemProductList.get(position);
                    itemProduct.setQuantity(itemProduct.getQuantity() + 1);
                    quantity.setText(String.valueOf(itemProduct.getQuantity()));
                    if (listener != null) {
                        listener.onUpdateCart();
                    }
                }
            });
            btn_tru.setOnClickListener(v->{
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ItemProduct itemProduct = itemProductList.get(position);
                    if (itemProduct.getQuantity() > 0) {
                        itemProduct.setQuantity(itemProduct.getQuantity() - 1);
                        quantity.setText(String.valueOf(itemProduct.getQuantity()));
                        if (listener != null) {
                            listener.onUpdateCart();
                        }
                    }
                }
            });
            if (heart != null) {
                heart.setOnClickListener(v-> {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ItemProduct itemProduct = itemProductList.get(position);
                        itemProduct.setCheck(heart.isChecked());
                    }
                });
            }
        }
    }

    public interface OnItemClickListener {
        void onUpdateCart();
    }
}
