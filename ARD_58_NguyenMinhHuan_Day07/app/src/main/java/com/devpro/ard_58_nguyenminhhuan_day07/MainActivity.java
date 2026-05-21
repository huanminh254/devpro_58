package com.devpro.ard_58_nguyenminhhuan_day07;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtAge;
    Button btnSubmit;
    private ActivityResultLauncher<Intent> launcher;
    private static final int REQUEST_CODE = 100;
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
        edtName = findViewById(R.id.edt_name);
        edtAge = findViewById(R.id.edt_age);
        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(v->
        {
            String name = edtName.getText().toString();
            String age = edtAge.getText().toString();
            Intent intent = new Intent(this, InfoAcitivity.class);
            intent.putExtra("name", name);
            intent.putExtra("age", age);
            launcher.launch(intent);
        });
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == REQUEST_CODE) {
                        Intent data = result.getData();
                        String name = data.getStringExtra("name");
                        String age = data.getStringExtra("age");
                        edtName.setText(name);
                        edtAge.setText(age);
                    }
                });
    }
}
