package com.example.ahmadnaghimfp.weatheraroundyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Rooms extends AppCompatActivity {

    ListView userIDList;
    Button findMeetPoint;
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();
    private DataCoy kelasCuy = new DataCoy();
    private DatabaseReference dataLala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rooms);
        userIDList = findViewById(R.id.list_user);
        findMeetPoint = findViewById(R.id.button_meet_point);
        dataLala = FirebaseDatabase.getInstance().getReference().child(JoinRoom.roomCode).child("Name");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mName);
        userIDList.setAdapter(arrayAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataLala.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                mName.add(value);
                String key = dataSnapshot.getKey();
                mKeys.add(key);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();

                int index = mKeys.indexOf(key);

                mName.set(index, value);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        findMeetPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kelasCuy.MeetPoint();
                Intent intent = new Intent(Rooms.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
