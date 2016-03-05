package com.greenApp.zaba37.dzienotwartypb.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.greenApp.zaba37.dzienotwartypb.Constants;
import android.view.MenuItem;

import com.greenApp.zaba37.dzienotwartypb.R;

public class ShowEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("" + getString(R.string.event_activity_title));

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_hardware_keyboard_backspace);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        switch (getIntent().getStringExtra("EVENT")){
            case Constants.ARCHITEKTURY_EVENT:
                setContentView(R.layout.event_architektury);
                break;
            case Constants.BUDOWNICTWA_I_SRODOWISKA_EVENT:
                setContentView(R.layout.event_budownictwa_i_srodowiska);
                break;
            case Constants.ELEKTRYCZNY_EVENT:
                setContentView(R.layout.event_elektryczny);
                break;
            case Constants.ENERGIA_WIOSNY_EVENT:
                setContentView(R.layout.event_energia_wiosny);
                break;
            case Constants.INFORMATYKI_EVENT:
                setContentView(R.layout.event_infotmatyki);
                break;
            case Constants.MECHANICZNY_EVENT:
                setContentView(R.layout.event_mechaniczny);
                break;
            case Constants.LESNY_EVENT:
                setContentView(R.layout.event_lesny);
                break;
            case Constants.ZARZADZANIA_EVENT:
                setContentView(R.layout.event_zarzadzania);
                break;
            case Constants.CENTRUM_REKTURTACJI_EVENT:
                setContentView(R.layout.event_centrum_rekrutacji);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
