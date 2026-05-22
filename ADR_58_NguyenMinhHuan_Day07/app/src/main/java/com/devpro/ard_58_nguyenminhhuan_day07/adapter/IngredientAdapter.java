package com.devpro.ard_58_nguyenminhhuan_day07.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.ard_58_nguyenminhhuan_day07.R;
import com.devpro.ard_58_nguyenminhhuan_day07.model.Item_Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private List<Item_Ingredient> ingredients;

    public IngredientAdapter(List<Item_Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredent, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Item_Ingredient ingredient = ingredients.get(position);
        holder.imgIngredient.setImageResource(ingredient.getImage());
        holder.tvName.setText(ingredient.getName());
        holder.tvQuantity.setText(ingredient.getQuantity());
    }

    @Override
    public int getItemCount() {
        return ingredients != null ? ingredients.size() : 0;
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIngredient;
        TextView tvName, tvQuantity;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIngredient = itemView.findViewById(R.id.image_ingredient_food);
            tvName = itemView.findViewById(R.id.name_ingredient_food);
            tvQuantity = itemView.findViewById(R.id.quantity_ingredient_food);
        }
    }
}
