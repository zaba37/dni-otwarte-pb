package com.example.zaba37.dniotwartepb.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.zaba37.dniotwartepb.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Display display = getWindowManager().getDefaultDisplay();
//        final Point point = new Point();
//        display.getSize(point);

//        final ImageView ivHeader = (ImageView) findViewById(R.id.ivHeader);
//
//        ivHeader.post(new Runnable() {
//            @Override
//            public void run() {
//              //  ivHeader.getLayoutParams().height = point.y/7;
//            }
//        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}
