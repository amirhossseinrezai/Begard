package com.example.begard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView =findViewById(R.id.navigation);
        drawerLayout =findViewById(R.id.draweLayout);

        actionToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.open,R.string.close);
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
        NavController controller = Navigation.findNavController(MainActivity.this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView,controller);
        switch (id){
            case R.id.profile://do somthing
                break;
            case R.id.settings://do somthing
                break;
            case  R.id.contactUs://do somthing
                break;
            case  R.id.about://do somthing
                break;
            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(MainActivity.this,Log_In.class);
                startActivity(intent);
        }
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }
    public void recyclerView(){
        ListFragment listFragment = new ListFragment();
        FragmentManager frmanager = getSupportFragmentManager();
        FragmentTransaction frTransaction = frmanager.beginTransaction();
        frTransaction.add(R.id.reMain,listFragment);
        //frTransaction.commit();
    }
}
