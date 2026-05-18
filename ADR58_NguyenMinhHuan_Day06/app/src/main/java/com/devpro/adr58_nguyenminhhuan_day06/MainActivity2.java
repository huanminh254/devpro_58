package com.devpro.adr58_nguyenminhhuan_day06;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.adr58_nguyenminhhuan_day06.adapter.ItemProductAdapter;
import com.devpro.adr58_nguyenminhhuan_day06.model.ItemProduct;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemProductAdapter cartAdapter;
    private TextView totalItem;
    private LinearLayout noItem;
    private CheckBox heart;
    private TextView subTotal;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnBack = findViewById(R.id.btn_back);
        totalItem = findViewById(R.id.total_item);
        recyclerView = findViewById(R.id.cart_item_product_activity2);
        heart = findViewById(R.id.heart_item_product);
        subTotal = findViewById(R.id.sub_total);
        total = findViewById(R.id.total);

        SharedPreferences sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        String json = sharedPreferences.getString("cart_data", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ItemProduct>>() {}.getType();
        noItem = findViewById(R.id.no_item);
        List<ItemProduct> itemProductList;
        if (json != null) {
            itemProductList = gson.fromJson(json, type);
        } else {
            itemProductList = new ArrayList<>();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new ItemProductAdapter(itemProductList, R.layout.item_product_cart, new ItemProductAdapter.OnItemClickListener() {
            @Override
            public void onUpdateCart() {
                updateTotal(itemProductList);
            }
        });
        recyclerView.setAdapter(cartAdapter);
        updateTotal(itemProductList);

        btnBack.setOnClickListener(v -> finish());
    }
    private void updateTotal(List<ItemProduct> list) {
        int sum = 0;
        float total = 0;
        for (ItemProduct item : list) {
            total += item.getQuantity() * Float.parseFloat(item.getPrice());
            sum += item.getQuantity();
        }
        if (sum == 0) {
            noItem.setVisibility(View.VISIBLE);
        } else {
            noItem.setVisibility(View.GONE);
        }
        totalItem.setText(String.valueOf(sum));
        subTotal.setText(String.valueOf(total));
        this.total.setText("$"+ total);
    }
}
