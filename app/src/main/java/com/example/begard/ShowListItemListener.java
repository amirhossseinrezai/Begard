package com.example.begard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowListItemListener extends AppCompatActivity  {
    TextView txtTitle,txtDescription,txtCosts;
    Data data= new Data();
    String[] lst ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_item_listener);
        txtTitle = findViewById(R.id.txtSentTitleFromMainActivity);
        txtCosts = findViewById(R.id.txtCost);
        txtDescription = findViewById(R.id.txt_Description);
        lst = new String[5];
        for (int i = 0 ; i < 5 ; i++) {
            lst[i]=data.getStrData()[i];
        }
        ViewPager viewPager =findViewById(R.id.viewPager);
        Intent intent =getIntent();
        if(intent.getExtras() != null){
            Data data = (Data) intent.getSerializableExtra("data");
            txtTitle.setText(data.getTitle());
            txtCosts.setText("250000"+" تومان ");
            txtDescription.setText(data.getDescription());
            ImageAdapter imageAdapter = new ImageAdapter(this,data.getmImage());
            viewPager.setAdapter(imageAdapter);
        }
    }
}