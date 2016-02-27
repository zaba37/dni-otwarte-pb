package com.example.zaba37.dniotwartepb.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.example.zaba37.dniotwartepb.fragment.HomePageFragment;
import com.example.zaba37.dniotwartepb.fragment.LocalizationFragment;
import com.example.zaba37.dniotwartepb.fragment.VisitedPlaceFragment;
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
    private LocalizationFragment localizationFragment;
    private VisitedPlaceFragment visitedPlaceFragment;
    private HomePageFragment homePageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationDataList = new ArrayList<>();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.listDrawer);
        fm = getSupportFragmentManager();

        final ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null)
        {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        navigationDataList.add(new DrawerItem(getString(R.string.home_page_navigation_menu), getResources().getDrawable(R.drawable.ic_navigation_black_24dp), Constants.HOME_PAGE_TAG));
        navigationDataList.add(new DrawerItem(getString(R.string.localization_navigation_menu), getResources().getDrawable(R.drawable.ic_navigation_black_24dp), Constants.LOCALIZATION_TAG));
        navigationDataList.add(new DrawerItem(getString(R.string.scaner_navigation_menu), getResources().getDrawable(R.drawable.ic_navigation_black_24dp), Constants.SCANER_TAG));

        drawerAdapter = new DrawerAdapter(this, R.layout.drawer_row, navigationDataList);
        mDrawerList.setAdapter(drawerAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        setDrawerToggle();

        getSupportActionBar().setTitle("" + getString(R.string.home_page_navigation_menu));

        if (homePageFragment == null) {
            homePageFragment = new HomePageFragment();
        }

        fm.beginTransaction().replace(R.id.fragmentContainer, homePageFragment).commit();
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
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_dehaze_black_24dp, R.string.drawer_open, R.string.drawer_close) {
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
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                }
                fm.beginTransaction().replace(R.id.fragmentContainer, homePageFragment).commit();
                drawerLayout.closeDrawers();
                break;
            case Constants.LOCALIZATION_TAG:
                getSupportActionBar().setTitle("" + name);
                if (localizationFragment == null) {
                    localizationFragment = new LocalizationFragment();
                }
                fm.beginTransaction().replace(R.id.fragmentContainer, localizationFragment).commit();
                drawerLayout.closeDrawers();
                break;
            case Constants.SCANER_TAG:
                getSupportActionBar().setTitle("" + name);
                if (visitedPlaceFragment == null) {
                    visitedPlaceFragment = new VisitedPlaceFragment();
                }
                fm.beginTransaction().replace(R.id.fragmentContainer, visitedPlaceFragment).commit();
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
