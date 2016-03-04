package com.example.zaba37.dniotwartepb.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zaba37.dniotwartepb.App;
import com.example.zaba37.dniotwartepb.Constants;
import com.example.zaba37.dniotwartepb.R;
import com.example.zaba37.dniotwartepb.adapter.DrawerAdapter;
import com.example.zaba37.dniotwartepb.event.OnFragmentLaunchingEvent;
import com.example.zaba37.dniotwartepb.fragment.MapFragment;
import com.example.zaba37.dniotwartepb.model.DrawerItem;

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

        drawerAdapter = new DrawerAdapter(this, R.layout.drawer_row, navigationDataList);
        mDrawerList.setAdapter(drawerAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        setDrawerToggle();

        getSupportActionBar().setTitle("" + getString(R.string.main_screen_title));

        if (mapFragment == null) {
            mapFragment = new MapFragment();
        }

        fm.beginTransaction().replace(R.id.fragmentContainer, mapFragment).commit();
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
