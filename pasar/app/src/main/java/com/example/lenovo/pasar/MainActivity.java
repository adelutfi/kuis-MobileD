package com.example.lenovo.pasar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.lenovo.pasar.Fragment.FragmentInsert;
import com.example.lenovo.pasar.Fragment.FragmentSearch;
import com.example.lenovo.pasar.Fragment.FragmentTampil;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    private static boolean openFirst = true;
    private static int navStatus = -1;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComp();
        if(savedInstanceState == null){
            openFirst = true;
            MenuItem item = navigationView.getMenu().getItem(0).setChecked(true);
            onNavigationItemSelected(item);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment f = null;
        int id = item.getItemId();

        if (id == R.id.nav_insert) {
            if(navStatus == 0 && !openFirst){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                openFirst = false;
                navStatus = 0;
                fab.show();
                f = new FragmentInsert();
            }
        } else if (id == R.id.nav_search) {
            if(navStatus == 1){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                fab.hide();
                navStatus = 1;
                f = new FragmentSearch();
            }
        }else if(id == R.id.nav_tampil){
            if(navStatus == 2){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                fab.hide();
                navStatus = 2;
                f = new FragmentTampil();
            }
        }else{
            openFirst = false;
            navStatus = 0;
            f = new FragmentInsert();
            fab.show();
        }

        if(f != null){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container_fragment, f);
            ft.commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void initComp(){
        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setSelected(true);
    }

}
