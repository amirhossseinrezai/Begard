package com.example.begard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
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
    TextView textView;
    List<String> lst = new ArrayList<>(Arrays.asList(new Data().getStrData()));
    ListView lstView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_item_listener);
        textView = findViewById(R.id.txtSentTitleFromMainActivity);
        lstView = findViewById(R.id.listView);

        ViewPager viewPager =findViewById(R.id.viewPager);
        Intent intent =getIntent();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lst);
        if(intent.getExtras() != null){
            Data data = (Data) intent.getSerializableExtra("data");
            lst.get(0).replace(lst.get(0),data.getTitle());
            //textView.setText(data.getTitle());
            ImageAdapter imageAdapter = new ImageAdapter(this,data.getmImage());
            viewPager.setAdapter(imageAdapter);
            lstView.onTouchModeChanged(false);
            lstView.setAdapter(arrayAdapter);
        }
    }
}