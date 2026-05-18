package com.devpro.myapplication;

import static android.text.TextUtils.substring;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tv_description;
    String tv_demo_description;
    boolean flag = true;
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
        tv_description = findViewById(R.id.tv_description);
        tv_demo_description = tv_description.getText().toString();
        tv_description.post(this::collapseDescription);
        tv_description.setOnClickListener(v->{
            if(flag){
                expandDescription();
            }else{
                collapseDescription();
            }
            flag = !flag;
        });
    }
    private void expandDescription() {
        tv_description.setMaxLines(Integer.MAX_VALUE);
        tv_description.setText(tv_demo_description);
    }
    private void collapseDescription() {
        if(tv_description.getLineCount() > 3){
            int lineIndex = tv_description.getLayout().getLineEnd(2);
            String suffix = " Read more...";
            String baseText = tv_demo_description.substring(0, Math.max(0,lineIndex-16));
            String fullText = baseText + suffix;
            
            SpannableString spannableString = new SpannableString(fullText);
            int start = baseText.length();
            int end = fullText.length();
            
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            
            tv_description.setText(spannableString);
        }
    }
}