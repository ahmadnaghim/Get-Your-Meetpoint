package com.example.ahmadnaghimfp.weatheraroundyou;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataCoy {
    private DatabaseReference dataPertama;
    private DatabaseReference dataKedua;
    private DatabaseReference dataKetiga;

    DataCoy(){
        dataPertama= FirebaseDatabase.getInstance().getReference();
        dataKedua = FirebaseDatabase.getInstance().getReference();
        dataKetiga = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDataPertama() {
        return dataPertama;
    }

    public DatabaseReference getDataKedua() {
        return dataKedua;
    }

    public DatabaseReference getDataKetiga() {
        return dataKetiga;
    }

    public void joinCode(String s){
        dataKedua = FirebaseDatabase.getInstance().getReference().child(s).child("Name");
        dataKedua.push().setValue(MainActivity.name);

        dataKedua = FirebaseDatabase.getInstance().getReference().child(s).child("LatLng").child("Lat");
        dataPertama = FirebaseDatabase.getInstance().getReference().child(s).child("LatLng").child("Lat").child("SumLat");
        combineLatLng(dataPertama, dataKedua, "Lat");

        dataKedua = FirebaseDatabase.getInstance().getReference().child(s).child("LatLng").child("Lng");
        dataPertama = FirebaseDatabase.getInstance().getReference().child(s).child("LatLng").child("Lng").child("SumLng");
        combineLatLng(dataPertama, dataKedua, "Lng");

        dataKedua =FirebaseDatabase.getInstance().getReference().child(s).child("UserCount");
        dataPertama = FirebaseDatabase.getInstance().getReference().child(s).child("UserCount").child("Count");
        countUser(dataPertama, dataKedua);
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
                        double x = dataSnapshot.getValue(Double.class);
                        x+=MainActivity.latitude;
                        dataKedua.child("SumLat").setValue(x);
                    }
                    else{
                        double x = dataSnapshot.getValue(Double.class);
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
                    double x = dataSnapshot.getValue(Double.class);
                    lalaCoy.child("Count").setValue(x+1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void MeetPoint(){
        dataPertama = FirebaseDatabase.getInstance().getReference().child(JoinRoom.roomCode).child("LatLng").child("Lat").child("SumLat");
        dataKedua = FirebaseDatabase.getInstance().getReference().child(JoinRoom.roomCode).child("LatLng").child("Lng").child("SumLng");
        dataKetiga = FirebaseDatabase.getInstance().getReference().child(JoinRoom.roomCode).child("UserCount").child("Count");
    }
}