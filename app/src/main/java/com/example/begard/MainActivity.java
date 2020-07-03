package com.example.begard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText edtName, edtEmail, edtID, edtNumber;
    Button btnInsert, btnView, btnUpdate, btnDelete;
    DatabaseManager dbm;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbm = new DatabaseManager(this);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.draweLayout);
        edtID = (EditText) findViewById(R.id.edtID);
        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnView = (Button) findViewById(R.id.btnView);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mID = edtID.getText().toString();
                String mName = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String number = edtNumber.getText().toString();
                if (TextUtils.isEmpty(mID) || TextUtils.isEmpty(mName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(number)){

                    Toast.makeText(MainActivity.this, "تمامی فیلدها باید تکمیل شود", Toast.LENGTH_LONG).show();

                } else {

                    User user = new User();
                    user.UserID = mID;
                    user.fullName = mName;
                    user.email = email;
                    user.number = number;
                    dbm.insertUser(user);
                    Toast.makeText(MainActivity.this, "اطلاعات با موفقیت ذخیره شد", Toast.LENGTH_LONG).show();
                }
            }
        });


        actionToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        actionToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionToggle);
        actionToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        NavController controller = Navigation.findNavController(MainActivity.this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, controller);
        switch (id) {
            case R.id.profile://do somthing
                break;
            case R.id.settings://do somthing
                break;
            case R.id.contactUs://do somthing
                break;
            case R.id.about://do somthing
                break;
            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, Log_In.class);
                startActivity(intent);
        }
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void recyclerView() {
        MainFragment mainFragment = new MainFragment();
        ListFragment listFragment = new ListFragment();
        FragmentManager frmanager = getSupportFragmentManager();
        FragmentTransaction frTransaction = frmanager.beginTransaction();
        frTransaction.add(R.id.reMain, listFragment);
        //frTransaction.commit();
    }
}

