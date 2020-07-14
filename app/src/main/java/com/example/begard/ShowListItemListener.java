package com.example.begard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowListItemListener extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_item_listener);

        textView = findViewById(R.id.txtget);
        Intent intent =getIntent();

        if(intent.getExtras() != null){
            Data data = (Data) intent.getSerializableExtra("data");
            textView.setText(data.getTitle());
        }
    }
}