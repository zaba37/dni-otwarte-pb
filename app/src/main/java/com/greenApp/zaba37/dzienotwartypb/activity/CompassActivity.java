package com.greenApp.zaba37.dzienotwartypb.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenApp.zaba37.dzienotwartypb.R;

/**
 * Created by Marcin on 02.03.2016.
 */
public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private TextView distance;
    private TextView name;
    private ImageView imgCompass;
    private ImageView imgWaiting;
    private float currentDegree;
    private SensorManager sensorManager;

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private GpsStatus.Listener gpsStatusListener;
    private double targetLongtitude, targetLatitude;
    private Location myLocation, targetLocation;
    private GeomagneticField geoField;
    private boolean hasGPSFix;
    private long mLastLocationMillis;
    private boolean isActivityJustLaunched;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isActivityJustLaunched = true;

        setContentView(R.layout.compass);
        imgCompass = (ImageView) findViewById(R.id.imgCompass);
        imgWaiting = (ImageView) findViewById(R.id.waiting);
        distance = (TextView) findViewById(R.id.distance);
        name = (TextView) findViewById(R.id.txtTarget);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            targetLatitude = extras.getDouble("LATITUDE");
            targetLongtitude = extras.getDouble("LONGTITUDE");
            name.setText(extras.getString("TITLE"));
        }

        currentDegree = 0f;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        targetLocation = new Location("dummyprovider");
        targetLocation.setLatitude(targetLatitude);
        targetLocation.setLongitude(targetLongtitude);

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Lokalizacja");
        alertDialogBuilder.setMessage("Włącz lokalizację (GPS i sieci)");


        alertDialogBuilder.setPositiveButton("Ustawienia", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        alertDialogBuilder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                finish();
            }
        });

        alertDialog = alertDialogBuilder.create();

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
            }

            @Override
            public void onProviderDisabled(String provider) {
                alertDialog.show();
            }
        };

        gpsStatusListener = new GpsStatus.Listener() {
            @Override
            public void onGpsStatusChanged(int event) {
                switch (event){
                    case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                        if (myLocation != null) {
                            if ((SystemClock.elapsedRealtime() - mLastLocationMillis) < 20000) {
                                if (!hasGPSFix)
                                    Toast.makeText(getApplicationContext(), "Uzyskano sygnał GPS", Toast.LENGTH_LONG).show();
                                imgWaiting.setVisibility(4);
                                hasGPSFix = true;
                            } else {
                                if (hasGPSFix)
                                    Toast.makeText(getApplicationContext(), "Utracono sygnał GPS", Toast.LENGTH_LONG).show();
                                imgWaiting.setVisibility(1);
                                hasGPSFix = false;
                            }
                        }
                        break;
                }
            }
        };


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);

            }
            else{
                locationManager.requestLocationUpdates("gps", 500, 1, locationListener);
                locationManager.addGpsStatusListener(gpsStatusListener);
            }

        } else {
            locationManager.requestLocationUpdates("gps", 500, 1, locationListener);
            locationManager.addGpsStatusListener(gpsStatusListener);
        }
        /*locationManager.addGpsStatusListener(new GpsStatus.Listener() {
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


        });*/



        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_hardware_keyboard_backspace);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("Kompas");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates("gps", 500, 1, locationListener);
                    locationManager.addGpsStatusListener(gpsStatusListener);
                }
                else {
                    finish();
                }
                return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();



        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);


        if(!isActivityJustLaunched){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET
                    }, 10);

                }
                else{
                    locationManager.requestLocationUpdates("gps", 500, 1, locationListener);
                    locationManager.addGpsStatusListener(gpsStatusListener);
                }
            } else {
                locationManager.requestLocationUpdates("gps", 500, 1, locationListener);
                locationManager.addGpsStatusListener(gpsStatusListener);
            }
        }


        isActivityJustLaunched = false;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(!isActivityJustLaunched){
            sensorManager.unregisterListener(this);
            locationManager.removeUpdates(locationListener);
            locationManager.removeGpsStatusListener(gpsStatusListener);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
            if (myLocation != null && hasGPSFix) {

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
