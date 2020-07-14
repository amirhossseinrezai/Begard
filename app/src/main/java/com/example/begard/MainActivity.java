package com.example.begard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionToggle;
    NavigationView navigationView;
    Toolbar toolBar;
    List<Data> dtaUser = new ArrayList<>();
    ListAdapter listAdapter;
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

        //setRecyclerView();
        setDrawerLayout();
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
        int id = item.getItemId();
        if (actionToggle.onOptionsItemSelected(item)) {
            return true;
        }else if (id == R.id.search_icon){
            return  true;
        }
        return  super.onOptionsItemSelected(item);
    }
    public void setDrawerLayout(){
        ListFragment listFragment = new ListFragment();
        FragmentManager frmanager = getSupportFragmentManager();
        FragmentTransaction frTransaction = frmanager.beginTransaction();
        frTransaction.add(R.id.draweLayout,listFragment);
        //frTransaction.commit();
    }
   /* public void setRecyclerView(){
       // View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = findViewById(R.id.recycle);
        String[] strData = new String[]{"Ali","Amir","Hosein","Mohammad","Kian","Illia"};
        for (String str : strData) {
            dtaUser.add(new Data(str));
        }
        listAdapter = new ListAdapter((ListAdapter.onAdapterListener) MainActivity.this,dtaUser);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }*/

   /* @Override
    public void onAdapterListener(int position) {
        Intent intent = new Intent(getApplicationContext(),ShowListItemListener.class);
        startActivity(intent);
    }*/
}
