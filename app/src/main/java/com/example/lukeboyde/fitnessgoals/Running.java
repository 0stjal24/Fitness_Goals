package com.example.lukeboyde.fitnessgoals;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Running extends AppCompatActivity {

    private OdometerService odometer;
    private boolean bound = false;
    Button startBtn, stopBtn;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            OdometerService.OdometerBinder odometerBinder =
                    (OdometerService.OdometerBinder) binder;
            odometer = odometerBinder.getOdometer();
            bound = true;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        startBtn = (Button)findViewById(R.id.stt_button);
        stopBtn = (Button)findViewById(R.id.stp_button);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStart();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStop();
            }
        });
        watchMileage();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, OdometerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;
        }

    }

    private void watchMileage() {
        final TextView distanceView = (TextView)findViewById(R.id.distance);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0.0;
                if (odometer != null) {
                    distance = odometer.getMiles();
                }
                String distanceStr = String.format("%1$,.2f miles", distance);
                distanceView.setText(distanceStr);
                handler.postDelayed(this, 1000);
            }
        });

        //when stop save in sqlite database
        //get date and distance
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_workout:
                //code to run when the create workout item is clicked
                return true;
            case R.id.action_choose_workout:
                //code to run when the choose workout item is clicked
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                return true;
            case R.id.action_pictures:
                //code to run when the picture item is clicked
                intent = new Intent(this, Camera.class);
                startActivity(intent);
                return true;
            case R.id.action_stopwatch:
                //code to run when the stopwatch item is clicked
                intent = new Intent(this, StopwatchActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_run:
                //code to run when track run item is clicked
                intent = new Intent(this, Running.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
