package com.devpro.ard_58_nguyenminhhuan_day07;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InfoAcitivity extends AppCompatActivity {
    Button btnBack;
    TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_acitivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBack = findViewById(R.id.btn_back);
        tvInfo = findViewById(R.id.tv_info);
        btnBack.setOnClickListener(v->
        {
            finish();
        });
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
        tvInfo.setText("Name: " + name + "\nAge: " + age);
    }
}