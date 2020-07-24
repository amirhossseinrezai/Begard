package com.example.begard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionToggle;
    NavigationView navigationView;
    Toolbar toolBar;
    private boolean isItem = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");

        navigationView =findViewById(R.id.navigation);
        drawerLayout =findViewById(R.id.draweLayout);

        actionToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolBar,R.string.open,R.string.close);
        actionToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionToggle);
        actionToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        final SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setQueryHint("Search Here!");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                MainFragment.listAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        NavController controller = Navigation.findNavController(MainActivity.this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView,controller);
        switch (id){
            /*case  R.id.home:
                setDrawerLayout(new MainFragment());
                break;
            case R.id.profile:
                setDrawerLayout(new ProfileFragment());
                break;
            case R.id.settings:
                setDrawerLayout(new SettingsFragment());
                break;
            case  R.id.about:
                setDrawerLayout(new AboutFragment());
                break;
            case R.id.RegisterPlace:
                setDrawerLayout(new AddingDataByUserFragment());
                break;*/
            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(MainActivity.this,Log_In.class);
                startActivity(intent);
                break;
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (actionToggle.onOptionsItemSelected(item)) {
            return true;
        }else if (id == R.id.search_icon){
            return  true;
        }
        return  super.onOptionsItemSelected(item);
    }
    public void setDrawerLayout(Fragment fragment){
        FragmentManager frmanager = getSupportFragmentManager();
        FragmentTransaction frTransaction = frmanager.beginTransaction();
        frTransaction.add(R.id.draweLayout,fragment);
    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(MainActivity.this);
        aBuilder.setMessage("Are You Sure?").setCancelable(false)
                .setPositiveButton("Yee", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
                Log.e("LOG","onClick Yes Called");
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = aBuilder.create();
        alertDialog.show();
    }
}
