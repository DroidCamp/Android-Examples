package xyz.hemangkumar.rnfapp;

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
import android.widget.Toast;

import java.sql.Time;

import xyz.hemangkumar.rnfapp.fragments.About;
import xyz.hemangkumar.rnfapp.fragments.Board;
import xyz.hemangkumar.rnfapp.fragments.Calculator;
import xyz.hemangkumar.rnfapp.fragments.Home;
import xyz.hemangkumar.rnfapp.fragments.PostWorkshop;
import xyz.hemangkumar.rnfapp.fragments.TimeTable;
import xyz.hemangkumar.rnfapp.fragments.Workshop;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //This needed to be created in order to make navigation drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initializing Objects and Connecting layouts to view objects whenever required */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);

        /* Adding Listeners, Setting up Toolbar */
        setSupportActionBar(toolbar);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView.setNavigationItemSelectedListener(this);


        /* Now overriding onPostCreate so that Hamburger Icon comes */
        /* Override onOptionsItemSelected to handle touch event on Hamburger Icon */

        /* Open Home Fragment */
        if(savedInstanceState == null){
            FragmentManager fragmentManger = getSupportFragmentManager();
            fragmentManger.beginTransaction().replace(R.id.frame_layout, Home.newInstance()).commit();
            getSupportActionBar().setTitle("Home");
        }


    }

    /* This function handles nav drawer clicks */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        switch(item.getItemId()){

            case R.id.about_list: fragment = About.newInstance();
                break;

            case R.id.board_list: fragment = Board.newInstance();
                break;

            case R.id.calculator_list: fragment = Calculator.newInstance();
                break;

            case R.id.home_list: fragment = Home.newInstance();
                break;

            case R.id.workshop_list: fragment = Workshop.newInstance();
                break;

            case R.id.tt_list: fragment = TimeTable.newInstance();
                break;

            case R.id.post_workshop_list: fragment = PostWorkshop.newInstance();
                break;

            default: fragment = Home.newInstance();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

        item.setChecked(true);
        if(getSupportActionBar() !=null){
            getSupportActionBar().setTitle(item.getTitle());
        }

        drawerLayout.closeDrawers();

        return true;

    }

    /* This shows Hamburger icon */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    /* This one opens drawer on touching Hamburger Icon */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : drawerLayout.openDrawer(GravityCompat.START);
                break;

            //it can handle other action bar item clicks too

        }
        return super.onOptionsItemSelected(item);
    }
}
