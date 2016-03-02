package com.example.zaba37.dniotwartepb.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zaba37.dniotwartepb.R;

/**
 * Created by Marcin on 02.03.2016.
 */
public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private TextView distance;
    private ImageView imgCompass;
    private float currentDegree;
    private SensorManager sensorManager;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private double targetLongtitude, targetLatitude;
    private Location myLocation, targetLocation;
    private GeomagneticField geoField;
    private boolean hasGPSFix;
    private long mLastLocationMillis;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //targetLatitude = 53.136975; PIZZERIA
        //targetLongtitude = 23.175302;
        //53.117562, 23.142329 SPOLEM
        //targetLatitude = 53.117562;
        //targetLongtitude = 23.142329;
        //53.115956, 23.145271 HOTEL
        //targetLatitude = 53.115956;
        //targetLongtitude = 23.145271;
        //53.117856, 23.148672
        //targetLatitude = 53.117856;
        //targetLongtitude = 23.148672;
        //53.117234, 23.146783 WI
        //KRASZ 53.137167, 23.174905
        currentDegree = 0f;
        targetLongtitude = 23.174905; //lokalizacja przy moim bloku, do testowania
        targetLatitude = 53.137167;
        setContentView(R.layout.activity_main);
        imgCompass = (ImageView) findViewById(R.id.imgCompass);
        distance = (TextView) findViewById(R.id.distance);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        targetLocation = new Location("dummyprovider");
        targetLocation.setLatitude(targetLatitude);
        targetLocation.setLongitude(targetLongtitude);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                myLocation = location;
                mLastLocationMillis = SystemClock.elapsedRealtime();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                //nieuzywane, jest GPSstatus listener
            }

            @Override
            public void onProviderEnabled(String provider) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.wskazowki, getApplicationContext().getTheme()));
                } else {
                    imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.wskazowki));
                }
            }

            @Override
            public void onProviderDisabled(String provider) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.napis, getApplicationContext().getTheme()));
                } else {
                    imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.napis));
                }
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);

                return;
            }
        } else {
            locationManager.requestLocationUpdates("gps", 500, 3, locationListener);
        }
        locationManager.addGpsStatusListener(new GpsStatus.Listener() {
            @Override
            public void onGpsStatusChanged(int event) {

                switch (event) {
                    case GpsStatus.GPS_EVENT_STARTED:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.wskazowki, getApplicationContext().getTheme()));
                        } else {
                            imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.wskazowki));
                        }
                        return;
                    case GpsStatus.GPS_EVENT_STOPPED:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.napis, getApplicationContext().getTheme()));
                        } else {
                            imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.napis));
                        }
                        return;
                    case GpsStatus.GPS_EVENT_FIRST_FIX:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.wskazowki, getApplicationContext().getTheme()));
                        } else {
                            imgCompass.setImageDrawable(getResources().getDrawable(R.drawable.wskazowki));
                        }
                        return;
                    case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                        if (myLocation != null) {
                            if ((SystemClock.elapsedRealtime() - mLastLocationMillis) < 20000) {
                                if (!hasGPSFix)
                                    Toast.makeText(getApplicationContext(), "Uzyskano sygnał GPS", Toast.LENGTH_LONG).show();
                                hasGPSFix = true;
                            } else {
                                if (hasGPSFix)
                                    Toast.makeText(getApplicationContext(), "Utracono sygnał GPS", Toast.LENGTH_LONG).show();

                                hasGPSFix = false;
                            }
                        }
                        return;
                }
            }


        });
        locationManager.requestLocationUpdates("gps", 500, 3, locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates("gps", 500, 3, locationListener);
                }
                return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
        locationManager.requestLocationUpdates("gps", 500, 3, locationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (myLocation != null) {
            geoField = new GeomagneticField(
                    Double.valueOf(myLocation.getLatitude()).floatValue(),
                    Double.valueOf(myLocation.getLongitude()).floatValue(),
                    Double.valueOf(myLocation.getAltitude()).floatValue(),
                    System.currentTimeMillis()
            );

            float degree = event.values[0];
            degree += geoField.getDeclination();
            float bearing = myLocation.bearingTo(targetLocation);
            float direction = degree - bearing;

            distance.setText(((int) myLocation.distanceTo(targetLocation)) + " m");
            RotateAnimation ra = new RotateAnimation(currentDegree, -direction, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            ra.setDuration(120);
            ra.setFillAfter(true);
            imgCompass.startAnimation(ra);
            currentDegree = -direction;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor p1, int p2) {
        //nieuzywane
    }

}
