package com.example.kundjanasiththonglek.loginfirebase;

import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class ViewLocation extends AppCompatActivity {
    private Button getLocation, stopGPS;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private int counter = 1;
    GPSTracker gps;

    Handler h = new Handler();
    Thread task;
    private long startTime;
    private String timeString;
    private TextView timerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
        timerView = (TextView) findViewById(R.id.timerText);
        getLocation = (Button) findViewById(R.id.getLocation);
        stopGPS = (Button) findViewById(R.id.stopGPS);
        try{
            if(ActivityCompat.checkSelfPermission(this, mPermission)!= PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{mPermission},REQUEST_CODE_PERMISSION);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        stopGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopHandlerTask();
                timerView.setText("Location Service is Stopped");
            }
        });
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                counter = 1;
            }
        });

    }
    public void startTimer() {
        startTime = System.currentTimeMillis();
        task = new Thread() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                long secs = millis / 1000 % 60;
                timeString = String.format("%02d", secs);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timerView.setText(timeString);
                    }
                });
                h.postDelayed(task, 1000);
            }
        };
        h.postDelayed(task,1000);
    }
    public void stopHandlerTask() {
        h.removeCallbacks(task);
    }
//        getLocation = (Button) findViewById(R.id.getLocation);
//        getLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gps = new GPSTracker(ViewLocation.this);
//                if(gps.canGetLocation()){
//                    double latitude = gps.getLatitude();
//                    double longitude = gps.getLongitude();
//                    Toast.makeText(getApplicationContext(),counter + "Current location is \n Lat: "+latitude+"\nLong: "+longitude,Toast.LENGTH_LONG).show();
//                }else{
//                    gps.showSettingsAlert();
//                }
//                counter++;
//            }
//        });
//    }
}
