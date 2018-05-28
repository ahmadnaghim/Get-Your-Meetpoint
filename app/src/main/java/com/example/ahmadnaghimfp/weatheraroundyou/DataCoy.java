package com.example.ahmadnaghimfp.weatheraroundyou;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataCoy {
    private DatabaseReference dataCuy;
    private DatabaseReference refRoomName;
    private String xyz;

    DataCoy(){
        dataCuy= FirebaseDatabase.getInstance().getReference();
        refRoomName = FirebaseDatabase.getInstance().getReference();
    }



    public void joinCode(String s){
        refRoomName= FirebaseDatabase.getInstance().getReference().child(s).child("Name");
        refRoomName.push().setValue(MainActivity.name);

        refRoomName= FirebaseDatabase.getInstance().getReference().child(s).child("LatLng").child("Lat");
        dataCuy = FirebaseDatabase.getInstance().getReference().child(s).child("LatLng").child("Lat").child("SumLat");
        combineLatLng(dataCuy, refRoomName, "Lat");

        refRoomName= FirebaseDatabase.getInstance().getReference().child(s).child("LatLng").child("Lng");
        dataCuy = FirebaseDatabase.getInstance().getReference().child(s).child("LatLng").child("Lng").child("SumLng");
        combineLatLng(dataCuy, refRoomName, "Lng");

        refRoomName=FirebaseDatabase.getInstance().getReference().child(s).child("UserCount");
        dataCuy = FirebaseDatabase.getInstance().getReference().child(s).child("UserCount").child("Count");
        countUser(dataCuy, refRoomName);
    }

    public void combineLatLng(DatabaseReference ayoCoy, DatabaseReference dataKedua, String type){
        ayoCoy.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()== null){
                    if(type=="Lat"){
                        dataKedua.child("SumLat").setValue(MainActivity.latitude);
                    }
                    else{
                        dataKedua.child("SumLng").setValue(MainActivity.longitude);
                    }
                }
                else{
                    if(type=="Lat"){
                        Double x = dataSnapshot.getValue(Double.class);
                        x+=MainActivity.latitude;
                        dataKedua.child("SumLat").setValue(x);
                    }
                    else{
                        Double x = dataSnapshot.getValue(Double.class);
                        x+=MainActivity.longitude;
                        dataKedua.child("SumLng").setValue(x);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void countUser(DatabaseReference dataTmbhan, DatabaseReference lalaCoy){
        dataTmbhan.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()==null){
                    double x = 1.0;
                    lalaCoy.child("Count").setValue(x);
                }
                else{
                    Double x = dataSnapshot.getValue(Double.class);
                    lalaCoy.child("Count").setValue(x+1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void MeetPoint(){
        refRoomName = FirebaseDatabase.getInstance().getReference().child(JoinRoom.roomCode).child("LatLng").child("Lat").child("SumLat");
        takeValueLatLng(refRoomName, "Lat");
        refRoomName = FirebaseDatabase.getInstance().getReference().child(JoinRoom.roomCode).child("LatLng").child("Lng").child("SumLng");
        takeValueLatLng(refRoomName, "Lng");
        refRoomName = FirebaseDatabase.getInstance().getReference().child(JoinRoom.roomCode).child("UserCount").child("Count");
        takeValueLatLng(refRoomName, "Count");
        MainActivity.sumlatitude/=MainActivity.count;
        MainActivity.sumlongitude/=MainActivity.count;
    }

    private void takeValueLatLng(DatabaseReference refCoy, String type){
        refCoy.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double x = dataSnapshot.getValue(Double.class);
                if(type=="Lat"){
                    MainActivity.sumlatitude=x;
                }
                else if (type=="Lng"){
                    MainActivity.sumlongitude=x;
                }
                else{
                    MainActivity.count=x;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}