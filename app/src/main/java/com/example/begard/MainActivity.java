package com.example.begard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toast;

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
    EditText edtName, edtEmail, edtID, edtNumber;
    Button btnInsert, btnView, btnUpdate, btnDelete;
    DatabaseManager dbm;
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
        dbm = new DatabaseManager(this);
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
        NavController controller = Navigation.findNavController(MainActivity.this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView,controller);
        //setRecyclerView();
        //setDrawerLayout();
        setDrawerLayout();
        edtID = (EditText) findViewById(R.id.edtID);
        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnView = (Button) findViewById(R.id.btnView);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnInsert.setOnClickListener(new View.OnClickListener(){
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

        switch (id){
            case R.id.profile://do somthing
                setDrawerLayout(new ProfileFragment());
                break;
            case R.id.settings://do somthing
                setDrawerLayout(new SettingsFragment());
                break;
            case  R.id.contactUs://do somthing
                setDrawerLayout(new ContactUsFragment());
                break;
            case  R.id.about://do somthing
                setDrawerLayout(new AboutFragment());
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
    public void setDrawerLayout(Fragment fragment){
        ListFragment listFragment = new ListFragment();
        FragmentManager frmanager = getSupportFragmentManager();
        FragmentTransaction frTransaction = frmanager.beginTransaction();
        frTransaction.add(R.id.draweLayout,fragment);
        frTransaction.commit();
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
        //super.onBackPressed();
    }
}
