package com.example.marbeelz.ptdutamancagraha;

//import android.support.v7.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class UserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_user);
        Toolbar toolbar = findViewById(R.id.toolbar2_user);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout_user);
        NavigationView navigationView = findViewById(R.id.nav_view_user);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new UserFragment_1()).commit();
            navigationView.setCheckedItem(R.id.nav_1);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UserFragment_1()).addToBackStack(null).commit();
                break;
            case R.id.nav_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UserFragment_2()).addToBackStack(null).commit();
                break;
            case R.id.nav_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UserFragment_3()).addToBackStack(null).commit();
                break;
            case R.id.nav_2_1:
                Toast.makeText(this, "Item 2_1 Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_2_2:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Yakin?").setCancelable(true)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent x = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(x);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                ;
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void switchContent(int fragment_container, DetailUserFragment detail) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(fragment_container,detail);
        ft.addToBackStack(null);
        ft.commit();

    }
}
