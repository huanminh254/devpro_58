package com.devpro.ard_58_nguyenminhhuan_day07;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.ard_58_nguyenminhhuan_day07.adapter.IngredientAdapter;
import com.devpro.ard_58_nguyenminhhuan_day07.model.itemFood;
import com.devpro.ard_58_nguyenminhhuan_day07.viewmodel.ShareViewModel;

public class FragmentDetail extends Fragment{
    private ShareViewModel shareViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.image_item_detail_food);
        TextView tvName = view.findViewById(R.id.tv_name_detail_food);
        TextView tvRate = view.findViewById(R.id.tv_name_detail_rate);
        TextView tvTime = view.findViewById(R.id.tv_time_detail_time);
        TextView tvIngredientsCount = view.findViewById(R.id.tv_count_ingredients);
        CheckBox favouriteFood = view.findViewById(R.id.checkbox_favourite_detail);
        CheckBox cb1 = view.findViewById(R.id.rate_1);
        CheckBox cb2 = view.findViewById(R.id.rate_2);
        CheckBox cb3 = view.findViewById(R.id.rate_3);
        CheckBox cb4 = view.findViewById(R.id.rate_4);
        CheckBox cb5 = view.findViewById(R.id.rate_5);
        ImageButton btn_back = view.findViewById(R.id.btn_back_detail);
        RecyclerView recyclerViewIngredients = view.findViewById(R.id.recycle_ingredient);
        recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(getContext()));
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        shareViewModel.getSelectedFood().observe(getViewLifecycleOwner(), food->{
            if (food == null) return;
            imageView.setImageResource(food.getImage());
            tvName.setText(food.getName());
            tvRate.setText(String.valueOf(food.getRate()));
            tvTime.setText(food.getTimeFinish());
            tvIngredientsCount.setText("Ingredients (" + (food.getIngredients() != null ? food.getIngredients().size() : 0) + ")");
            favouriteFood.setChecked(food.isFavourite());
            
            if (food.getIngredients() != null) {
                recyclerViewIngredients.setAdapter(new IngredientAdapter(food.getIngredients()));
            }
        });
        favouriteFood.setOnClickListener(v->{
            itemFood food = shareViewModel.getSelectedFood().getValue();
            if(food!=null){
                food.setFavourite(favouriteFood.isChecked());
                shareViewModel.updateListFood(food);
            }
        });
        cb1.setOnClickListener(v->{
            itemFood food = shareViewModel.getSelectedFood().getValue();
            if(food!=null){
                cb1.setChecked(true);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
                cb5.setChecked(false);
                food.setRate(1);
                tvRate.setText("1");
                shareViewModel.updateListFood(food);
            }
        });
        cb2.setOnClickListener(v->{
            itemFood food = shareViewModel.getSelectedFood().getValue();
            if(food!=null){
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(false);
                cb4.setChecked(false);
                cb5.setChecked(false);
                tvRate.setText("2");
                food.setRate(2);
                shareViewModel.updateListFood(food);
            }
        });
        cb3.setOnClickListener(v->{
            itemFood food = shareViewModel.getSelectedFood().getValue();
            if(food!=null){
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(false);
                cb5.setChecked(false);
                tvRate.setText("3");
                food.setRate(3);
                shareViewModel.updateListFood(food);
            }
        });
        cb4.setOnClickListener(v->{
            itemFood food = shareViewModel.getSelectedFood().getValue();
            if(food!=null){
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(false);
                tvRate.setText("4");
                food.setRate(4);
                shareViewModel.updateListFood(food);
            }
        });
        cb5.setOnClickListener(v->{
            itemFood food = shareViewModel.getSelectedFood().getValue();
            if(food!=null){
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                tvRate.setText("5");
                food.setRate(5);
                shareViewModel.updateListFood(food);
            }
        });
        btn_back.setOnClickListener(v->{
            getParentFragmentManager().popBackStack();
        });

    }
}
