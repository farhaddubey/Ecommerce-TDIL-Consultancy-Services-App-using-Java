package com.tdilcs.tdilconsultancyservices;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private FrameLayout frameLayout;
    Toolbar toolbar;

    //FireBase Google File Not Installed according generated by SHA1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        frameLayout=findViewById(R.id.mainFrameLayout);
        setFragment(new HomeFragment());
        navigationView.getMenu().getItem(0).setChecked(true);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.nav_orders){
                    loadFragment(new AFragment());
                }else if(id==R.id.nav_cart){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_wishlist){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_account){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_website){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_software){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_design){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_ads){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_entrances){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_documentation){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_flats){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_skills){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_life_services){
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Fragment Shoutld be set", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.add(R.id.mainFrameLayout, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.main_search_icon){
            return true;
        } else if (id==R.id.main_notification_icon) {
            return true;
        } else if(id==R.id.main_cart_icon){
            return true;
        }
        return  super.onOptionsItemSelected(item);

    };
    private void setFragment(Fragment fragment){
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(frameLayout.getId(), fragment);
        ft.commit();
    }

}