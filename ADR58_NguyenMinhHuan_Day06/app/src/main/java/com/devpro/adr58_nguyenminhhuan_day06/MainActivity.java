package com.devpro.adr58_nguyenminhhuan_day06;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.adr58_nguyenminhhuan_day06.adapter.ItemProductAdapter;
import com.devpro.adr58_nguyenminhhuan_day06.model.ItemProduct;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemProductAdapter adapter;
    private List<ItemProduct> itemProductList;
    private TextView tv_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        itemProductList = new ArrayList<>();
        itemProductList.add(new ItemProduct("Mixed Black Coffee", "15.00", R.drawable.p1));
        itemProductList.add(new ItemProduct("Mixed Black Coffee", "18.00", R.drawable.p3));
        itemProductList.add(new ItemProduct("Hot Chocolate", "25.00", R.drawable.p5));
        itemProductList.add(new ItemProduct("Mixed Black Coffee", "20.00", R.drawable.p7));
        itemProductList.add(new ItemProduct("Bia", "22.00", R.drawable.p10));
        recyclerView = findViewById(R.id.recycler_item_product);
        tv_cart = findViewById(R.id.tv_cart);
        
        // Cập nhật: Thêm R.layout.item_product vào constructor của adapter
        adapter = new ItemProductAdapter(itemProductList, R.layout.item_product, new ItemProductAdapter.OnItemClickListener() {
            @Override
            public void onUpdateCart(){
                updateCart();
            }
        });
        
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        ImageButton btn_cart = findViewById(R.id.cart_item);

        btn_cart.setOnClickListener(v-> {
            saveData();
        });
        updateCart();
    }
    
    private void updateCart(){
        int total = 0;
        for(ItemProduct itemProduct : itemProductList){
            total+=itemProduct.getQuantity();
        }
        tv_cart.setText(String.valueOf(total));
    }

    private void saveData(){
        List<ItemProduct> itemProducts = new ArrayList<>();
        for (int i = 0; i < itemProductList.size(); i++) {
            if (itemProductList.get(i).getQuantity() > 0) {
                itemProducts.add(itemProductList.get(i));
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(itemProducts);
        SharedPreferences sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        sharedPreferences.edit().putString("cart_data", json).apply();
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}
