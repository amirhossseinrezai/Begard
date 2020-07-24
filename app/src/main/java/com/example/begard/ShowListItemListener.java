package com.example.begard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class ShowListItemListener extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    TextView txtTitle,txtDescription,txtCosts;
    Button btnRent;
    Data data= new Data();
    String[] lst ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_item_listener);
        txtTitle = findViewById(R.id.txtSentTitleFromMainActivity);
        txtCosts = findViewById(R.id.txtCost);
        txtDescription = findViewById(R.id.txt_Description);
        btnRent = findViewById(R.id.btnRent);
        btnRent.setOnClickListener(this);
        lst = new String[5];
        for (int i = 0 ; i < 5 ; i++) {
            lst[i]=data.getStrData()[i];
        }
        ViewPager viewPager =findViewById(R.id.viewPager);
        Intent intent =getIntent();
        if(intent.getExtras() != null){
            Data data = (Data) intent.getSerializableExtra("data");
            txtTitle.setText(data.getTitle());
            txtCosts.setText("250000"+"تومان ");
            txtDescription.setText(data.getDescription());
            ImageAdapter imageAdapter = new ImageAdapter(this,data.getmImage());
            viewPager.setAdapter(imageAdapter);
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        c.set(Calendar.DAY_OF_MONTH,i2);
        String strDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
    }

    @Override
    public void onClick(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(),"Date Picker");
    }
}