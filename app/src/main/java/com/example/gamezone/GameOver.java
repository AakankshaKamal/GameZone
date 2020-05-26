package com.example.gamezone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    private TextView scoreboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        int score=extras.getInt("SCORE");
        scoreboard=findViewById(R.id.score);
        scoreboard.setText("SCORE : "+score);
    }

}
