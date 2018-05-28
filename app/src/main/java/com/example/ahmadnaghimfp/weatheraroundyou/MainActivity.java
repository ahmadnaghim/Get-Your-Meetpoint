package com.example.ahmadnaghimfp.weatheraroundyou;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity
{

    EditText editTextUserName;
    Button buttonUserName;
    public static double latitude, longitude;
    private FusedLocationProviderClient mLocation;
    public static String name;
    public static double sumlatitude=0, sumlongitude=0, count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonUserName = findViewById(R.id.user_name_button);
        editTextUserName = findViewById(R.id.user_name);

        mLocation = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    latitude=location.getLatitude();
                    longitude=location.getLongitude();
                }
            }
        });

        buttonUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editTextUserName.getText().toString();
                Intent intent = new Intent(MainActivity.this, JoinRoom.class);
                startActivity(intent);
            }
        });


    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }
}