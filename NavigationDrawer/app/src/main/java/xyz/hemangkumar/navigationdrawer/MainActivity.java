package xyz.hemangkumar.navigationdrawer;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout navdrawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navdrawer = (DrawerLayout) findViewById(R.id.nav_drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, navdrawer, toolbar, R.string.open_d, R.string.close_d);

        navdrawer.addDrawerListener(actionBarDrawerToggle);

        nv = (NavigationView) findViewById(R.id.drawer);
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                navdrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        switch(item.getItemId()){
            case R.id.first_nav:
                fragment = OneFragment.newInstance("Hu", "Yo");
                break;

            case R.id.second_nav:
                fragment = TwoFragment.newInstance("YO", "Po");
                break;

            case R.id.third_nav:
                fragment = ThreeFragment.newInstance("UO", "LO");
                break;

            default:
                fragment = OneFragment.newInstance("", "");
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.frame_layout, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());

        navdrawer.closeDrawers();
        return true;
    }
}
