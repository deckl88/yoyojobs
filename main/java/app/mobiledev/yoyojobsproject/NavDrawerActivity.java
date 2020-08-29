package app.mobiledev.yoyojobsproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import app.mobiledev.yoyojobsproject.ui.home.HomeFragment;

public class NavDrawerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, new HomeFragment());
        ft.commit();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.menu_home, R.id.jobsSearch, R.id.nav_create_resume)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        
            int id = menuItem.getItemId();
            FragmentManager fm = getSupportFragmentManager();


            if (id == R.id.profile) {
                FragmentTransaction ft = fm.beginTransaction();

                Toast.makeText(NavDrawerActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.home) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, new HomeFragment());
                ft.commit();
                Toast.makeText(NavDrawerActivity.this, "Home", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.search) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new JobSearchFragment());
                ft.commit();
                Toast.makeText(NavDrawerActivity.this, "Job Search", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.create_resume) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new CreateResumeFragment());
                ft.commit();
                Toast.makeText(NavDrawerActivity.this, "Create Resume", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.contact) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //  ft.replace(R.id.frameLayout, new ContactUsFragment());
                ft.commit();
                Toast.makeText(NavDrawerActivity.this, "Contact Us", Toast.LENGTH_SHORT).show();
            }
            DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.dl);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }
}
