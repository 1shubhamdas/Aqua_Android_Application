package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Toolbar toolbar;

    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private FeedbackFragment feedbackFragment;
    private MaintenanceFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigation_view);

        //toolbar
        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        //fragment
        homeFragment = new HomeFragment ();
        feedbackFragment = new FeedbackFragment ();
        accountFragment = new MaintenanceFragment ();

        setFragment (homeFragment);


        //bottomnavigation
        bottomNavigationView = findViewById (R.id.bottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId ()) {

                    case R.id.home:
                        setFragment (homeFragment);
                        return true;

                    case R.id.feedback:
                        setFragment (feedbackFragment);
                        return true;

                    case R.id.profile:
                        setFragment (accountFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });


        //drawerlayout
        toggle = new ActionBarDrawerToggle (this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener (toggle);
        toggle.getDrawerArrowDrawable ().setColor (ContextCompat.getColor (getApplicationContext (), R.color.black));
        toggle.syncState ();
        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.about:
                        Intent intent = new Intent (getApplicationContext (), AboutActivity.class);
                        startActivity (intent);
                        drawerLayout.closeDrawer (GravityCompat.START);
                        break;

                    case R.id.timing:
                        Intent intent1 = new Intent (getApplicationContext (), TimingActivity.class);
                        startActivity (intent1);
                        drawerLayout.closeDrawer (GravityCompat.START);
                        break;

                    case R.id.account:
                        Intent intent2 = new Intent (getApplicationContext (), AccountActivity.class);
                        startActivity (intent2);
                        drawerLayout.closeDrawer (GravityCompat.START);
                        break;

                    case R.id.logout:
                        FirebaseAuth.getInstance ().signOut ();
                        Intent setupIntent = new Intent (getBaseContext (), MainActivity.class);
                        Toast.makeText (getBaseContext (), "Logged Out", Toast.LENGTH_LONG).show ();
                        setupIntent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity (setupIntent);
                        finish ();
                }
                return true;
            }
        });
    }

            //toolbar
            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater=getMenuInflater ();
                inflater.inflate (R.menu.toolbar,menu);
                return true;
            }

            //fragment
            public void setFragment(Fragment fragment){
                FragmentTransaction fragmentTransaction=getSupportFragmentManager ().beginTransaction ();
                fragmentTransaction.replace(R.id.mainframe,fragment);
                fragmentTransaction.commit ();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

}