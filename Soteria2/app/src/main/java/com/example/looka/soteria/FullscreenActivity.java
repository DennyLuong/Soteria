package com.example.looka.soteria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    String url = "jdbc:mysql://ec2-54-172-30-46.compute-1.amazonaws.com:3306/";
    String userName = "root";
    String password = "5aRQKp2O";
    Button startButton;
    ImageView image;
    TextView sense;
    BluetoothAdapter blueADP;
    Context context;
    Intent intent1;
    LocationManager locationManager;
    boolean GpsStatus;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        CheckGpsStatus();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        image = (ImageView) findViewById(R.id.image);
        startButton = (Button) findViewById(R.id.start);
        sense = (TextView) findViewById(R.id.sense);
        blueADP = BluetoothAdapter.getDefaultAdapter();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
                blueADP.enable();
                if (GpsStatus == false) {

                    intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent1);
                }
                wifiManager.setWifiEnabled(true);
                startColorAnimation(sense);
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */




    String dist;
    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
                try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, userName, password);
                Statement st = con.createStatement();
                String sql = "select * from client";


                final ResultSet rs = st.executeQuery("select * from client");
                rs.next();
                sense = (TextView) findViewById(R.id.sense);
                    dist.setText(rs.getString(2));

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void CheckGpsStatus() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void startColorAnimation(View v) {
        Random g = new Random();
        int colorStart;
        int colorEnd;
        int num = g.nextInt(50);
        for (int i = 0; i < 50; i++) {
            if (num > 25) {
                colorStart = 0xFFFFFFFF;
                colorEnd = 0xFFFF0000;
            } else {
                colorStart = 0xFFFF0000;
                colorEnd = 0xFFFFFFFF;
            }
            g = new Random();
            ValueAnimator colorAnim = ObjectAnimator.ofInt(v, "backgroundColor", colorStart, colorEnd);
            colorAnim.setDuration(2500);
            colorAnim.setEvaluator(new ArgbEvaluator());
            colorAnim.setRepeatCount(1);
            colorAnim.setRepeatMode(ValueAnimator.REVERSE);
            colorAnim.start();
        }
    }


}
