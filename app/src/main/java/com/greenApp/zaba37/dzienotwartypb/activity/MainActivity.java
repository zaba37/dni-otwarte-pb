package com.greenApp.zaba37.dzienotwartypb.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.greenApp.zaba37.dzienotwartypb.App;
import com.greenApp.zaba37.dzienotwartypb.Constants;
import com.greenApp.zaba37.dzienotwartypb.R;
import com.greenApp.zaba37.dzienotwartypb.adapter.DrawerAdapter;
import com.greenApp.zaba37.dzienotwartypb.event.OnFragmentLaunchingEvent;
import com.greenApp.zaba37.dzienotwartypb.fragment.AboutFragment;
import com.greenApp.zaba37.dzienotwartypb.fragment.MapFragment;
import com.greenApp.zaba37.dzienotwartypb.fragment.MrWallFragment;
import com.greenApp.zaba37.dzienotwartypb.fragment.PowerFragment;
import com.greenApp.zaba37.dzienotwartypb.model.DrawerItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerAdapter drawerAdapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView mDrawerList;
    private List<DrawerItem> navigationDataList;
    private FragmentManager fm;
    private MapFragment mapFragment;
    private MrWallFragment mrWallFragment;
    private PowerFragment powerFragment;
    private AboutFragment aboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationDataList = new ArrayList<>();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.listDrawer);
        fm = getSupportFragmentManager();

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_image_dehaze);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        navigationDataList.add(new DrawerItem(getString(R.string.main_screen_title), getResources().getDrawable(R.drawable.ic_navigation_black_24dp), Constants.HOME_PAGE_TAG));
        navigationDataList.add(new DrawerItem(getString(R.string.first_navigation_menu), getResources().getDrawable(R.drawable.ic_navigation_black_24dp), Constants.FIRST_TAG));
        navigationDataList.add(new DrawerItem(getString(R.string.second_navigation_menu), getResources().getDrawable(R.drawable.ic_navigation_black_24dp), Constants.SECOND_TAG));
        navigationDataList.add(new DrawerItem(getString(R.string.third_navigation_menu), getResources().getDrawable(R.drawable.ic_navigation_black_24dp), Constants.THIRD_TAG));
        navigationDataList.add(new DrawerItem(getString(R.string.about_navigation_meny), getResources().getDrawable(R.drawable.ic_navigation_black_24dp), Constants.ABOUT_TAG));

        drawerAdapter = new DrawerAdapter(this, R.layout.drawer_row, navigationDataList);
        mDrawerList.setAdapter(drawerAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        setDrawerToggle();

        getSupportActionBar().setTitle("" + getString(R.string.main_screen_title));

        if (mapFragment == null) {
            mapFragment = new MapFragment();
        }

        fm.beginTransaction().replace(R.id.fragmentContainer, mapFragment).commit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                }
                return;
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.getEventBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.getEventBus().unregister(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void setDrawerToggle() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_image_dehaze, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @SuppressWarnings("ConstantConditions")
    @Subscribe
    public void onEvent(OnFragmentLaunchingEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        String tag = drawerAdapter.getItem(event.getPosition()).getTag();
        String name = drawerAdapter.getItem(event.getPosition()).getItemName();

        switch (tag) {
            case Constants.HOME_PAGE_TAG:
                getSupportActionBar().setTitle("" + name);
                if (mapFragment == null) {
                    mapFragment = new MapFragment();
                }
                fm.beginTransaction().replace(R.id.fragmentContainer, mapFragment).commit();
                drawerLayout.closeDrawers();
                break;
            case Constants.FIRST_TAG:
                getSupportActionBar().setTitle("" + name);
                if (powerFragment == null) {
                    powerFragment = new PowerFragment();
                }
                mapFragment = null;
                fm.beginTransaction().replace(R.id.fragmentContainer, powerFragment).commit();
                drawerLayout.closeDrawers();
                break;
            case Constants.SECOND_TAG:
                getSupportActionBar().setTitle("" + name);
                if (mrWallFragment == null) {
                    mrWallFragment = new MrWallFragment();
                }
                mapFragment = null;
                fm.beginTransaction().replace(R.id.fragmentContainer, mrWallFragment).commit();
                drawerLayout.closeDrawers();
                break;
            case Constants.ABOUT_TAG:
                getSupportActionBar().setTitle("" + name);
                if (aboutFragment == null) {
                    aboutFragment = new AboutFragment();
                }
                mapFragment = null;
                fm.beginTransaction().replace(R.id.fragmentContainer, aboutFragment).commit();
                drawerLayout.closeDrawers();
                break;
            default:
                break;
        }
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            OnFragmentLaunchingEvent event = new OnFragmentLaunchingEvent(position);
            EventBus.getDefault().post(event);
        }
    }
}
