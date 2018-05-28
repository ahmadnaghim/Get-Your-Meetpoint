package com.example.ahmadnaghimfp.weatheraroundyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Rooms extends AppCompatActivity {

    ListView userIDList;
    Button findMeetPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        userIDList = findViewById(R.id.list_user);
        findMeetPoint = findViewById(R.id.button_meet_point);

        findMeetPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Method untuk cari nilai retrieve data dari database
            }
        });
    }
}
