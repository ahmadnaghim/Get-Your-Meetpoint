package com.example.ahmadnaghimfp.weatheraroundyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinRoom extends AppCompatActivity {

    private EditText joinRoomName;
    private Button joinBtn;
    private DataCoy LOLOLOL = new DataCoy();
    public static String roomCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        joinRoomName = findViewById(R.id.user_code);
        joinBtn =findViewById(R.id.user_code_button);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               roomCode = joinRoomName.getText().toString();
               LOLOLOL.joinCode(roomCode);
               Intent intent = new Intent(JoinRoom.this, Rooms.class);
               startActivity(intent);
            }
        });
    }
}
