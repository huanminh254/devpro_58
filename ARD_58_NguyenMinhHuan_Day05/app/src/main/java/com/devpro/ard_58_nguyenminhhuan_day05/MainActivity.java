package com.devpro.ard_58_nguyenminhhuan_day05;

import android.app.usage.StorageStatsManager;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.devpro.ard_58_nguyenminhhuan_day05.adapter.ImagePinterestAdapter;
import com.devpro.ard_58_nguyenminhhuan_day05.adapter.OptionPinterestAdapter;
import com.devpro.ard_58_nguyenminhhuan_day05.model.ImagePinterest;
import com.devpro.ard_58_nguyenminhhuan_day05.model.OptionPinterest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        List<OptionPinterest> list_option = new ArrayList<>(
                List.of(
                        new OptionPinterest("For You", true),
                        new OptionPinterest("Popular", false),
                        new OptionPinterest("Design", false),
                        new OptionPinterest("Technology", false),
                        new OptionPinterest("Photography", false)
                )
        );
        List<ImagePinterest> list_image = new ArrayList<>();
        for(int i = 1; i < 13; i++){
            String s = "image" + i;
            int id = getResources().getIdentifier(s, "drawable", getPackageName());
            list_image.add(new ImagePinterest(id));
        }
        OptionPinterestAdapter adapter = new OptionPinterestAdapter(list_option);
        RecyclerView recyclerViewOption = findViewById(R.id.rcv_pinterest);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewOption.setLayoutManager(linearLayoutManager);
        recyclerViewOption.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            for (int i = 0; i < list_option.size(); i++) {
                list_option.get(i).setSelected(i == position);
            }
            adapter.notifyDataSetChanged();
        });
        ImagePinterestAdapter adapter_image = new ImagePinterestAdapter(list_image);
        RecyclerView recyclerViewImage = findViewById(R.id.rcv_image_pinterest);
        StaggeredGridLayoutManager linearLayoutManager_image = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        recyclerViewImage.setLayoutManager(linearLayoutManager_image);
        recyclerViewImage.setAdapter(adapter_image);
    }
}