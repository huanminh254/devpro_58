package com.devpro.ard_58_nguyenminhhuan_day07;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.ard_58_nguyenminhhuan_day07.adapter.ItemFoodAdapter;
import com.devpro.ard_58_nguyenminhhuan_day07.adapter.OptionAdapter;
import com.devpro.ard_58_nguyenminhhuan_day07.model.Item_Ingredient;
import com.devpro.ard_58_nguyenminhhuan_day07.model.Option;
import com.devpro.ard_58_nguyenminhhuan_day07.model.itemFood;
import com.devpro.ard_58_nguyenminhhuan_day07.viewmodel.ShareViewModel;

import java.util.ArrayList;
import java.util.List;


public class FragmentList extends Fragment implements ItemFoodAdapter.OnFoodListener{
    private ShareViewModel shareViewModel;
    private RecyclerView rvFood, rvOption;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFood = view.findViewById(R.id.recycle_food);
        rvOption = view.findViewById(R.id.recycle_option);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        rvFood.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOption.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        initData();

        shareViewModel.getListFood().observe(getViewLifecycleOwner(), foods -> {
            if (foods != null) {
                rvFood.setAdapter(new ItemFoodAdapter(foods, this));
            }
        });
    }

    private void initData() {
        if (shareViewModel.getListFood().getValue() == null) {
            List<itemFood> list = new ArrayList<>();
            
            // Bò Kho
            List<Item_Ingredient> ing1 = new ArrayList<>();
            ing1.add(new Item_Ingredient(R.drawable.example, "Thịt bò", "500g"));
            ing1.add(new Item_Ingredient(R.drawable.example, "Cà rốt", "2 củ"));
            ing1.add(new Item_Ingredient(R.drawable.example, "Sả", "3 cây"));
            list.add(new itemFood(R.drawable.bo_kho, "Bò Kho", true, "45 min", 5, "Món bò kho thơm ngon đậm đà", ing1));

            // Bún Chả
            List<Item_Ingredient> ing2 = new ArrayList<>();
            ing2.add(new Item_Ingredient(R.drawable.example, "Thịt lợn", "400g"));
            ing2.add(new Item_Ingredient(R.drawable.example, "Bún tươi", "1kg"));
            ing2.add(new Item_Ingredient(R.drawable.example, "Nước mắm", "100ml"));
            list.add(new itemFood(R.drawable.bun_cha, "Bún Chả", false, "30 min", 4, "Bún chả Hà Nội truyền thống", ing2));

            // Cơm Tấm
            List<Item_Ingredient> ing3 = new ArrayList<>();
            ing3.add(new Item_Ingredient(R.drawable.example, "Gạo tấm", "500g"));
            ing3.add(new Item_Ingredient(R.drawable.example, "Sườn heo", "300g"));
            ing3.add(new Item_Ingredient(R.drawable.example, "Trứng gà", "1 quả"));
            list.add(new itemFood(R.drawable.com_tam, "Cơm Tấm", true, "20 min", 5, "Cơm tấm sườn bì chả", ing3));

            // Gỏi Cuốn
            List<Item_Ingredient> ing4 = new ArrayList<>();
            ing4.add(new Item_Ingredient(R.drawable.example, "Tôm", "200g"));
            ing4.add(new Item_Ingredient(R.drawable.example, "Thịt ba chỉ", "200g"));
            ing4.add(new Item_Ingredient(R.drawable.example, "Bánh tráng", "1 xấp"));
            list.add(new itemFood(R.drawable.goi_cuon, "Gỏi Cuốn", false, "15 min", 4, "Gỏi cuốn tôm thịt thanh mát", ing4));

            // Lẩu Thái
            List<Item_Ingredient> ing5 = new ArrayList<>();
            ing5.add(new Item_Ingredient(R.drawable.example, "Hải sản", "500g"));
            ing5.add(new Item_Ingredient(R.drawable.example, "Nấm các loại", "300g"));
            ing5.add(new Item_Ingredient(R.drawable.example, "Gia vị lẩu", "1 gói"));
            list.add(new itemFood(R.drawable.lau_thai, "Lẩu Thái", true, "60 min", 5, "Lẩu Thái chua cay", ing5));

            // Thêm các món còn lại với nguyên liệu tương ứng...
            list.add(new itemFood(R.drawable.mi_quang_ech, "Mì Quảng Ếch", false, "40 min", 4, "Mì quảng ếch Đà Nẵng", ing4));
            list.add(new itemFood(R.drawable.ga_chien_mam, "Gà Chiên Mắm", true, "25 min", 5, "Gà chiên nước mắm đậm đà", ing1));
            list.add(new itemFood(R.drawable.banh_sau_rieng, "Bánh Sầu Riêng", false, "30 min", 3, "Bánh crepe sầu riêng", ing3));
            list.add(new itemFood(R.drawable.banh_mi_sot_vang, "Bánh Mì Sốt Vang", true, "50 min", 5, "Bánh mì sốt vang nóng hổi", ing1));
            list.add(new itemFood(R.drawable.nem_nuong_nha_trang, "Nem Nướng Nha Trang", false, "35 min", 4, "Nem nướng Nha Trang đặc sản", ing2));

            shareViewModel.setListFood(list);
        }

        List<Option> options = new ArrayList<>();
        options.add(new Option("All", true));
        options.add(new Option("Fast Food", false));
        options.add(new Option("Healthy", false));
        options.add(new Option("Drinks", false));
        options.add(new Option("Desserts", false));
        rvOption.setAdapter(new OptionAdapter(options));
    }

    @Override
    public void onFoodClick(itemFood food){
        shareViewModel.setSelectedFood(food);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new FragmentDetail())
                .addToBackStack(null)
                .commit();
    }
}
