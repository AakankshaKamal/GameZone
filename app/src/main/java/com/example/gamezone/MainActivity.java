package com.example.gamezone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

   private FlyingFishView view;
   private Handler handler;
   private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new FlyingFishView(this);
        handler = new Handler();
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);
String str="WELCOME >>>>Try to cath the green and yellow balls as much as you can .They bear 10 and 20 score respectively>>";
String nst="BEWARE OF RED BALLS \n YOU HAVE THREE LIFE SO GET SET GO  ";
        builder.setMessage(str+nst);
        builder.setTitle("INSTRUCTIONS");
        builder.setIcon(R.drawable.fishicon);

        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        "OK",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // When the user click yes button
                                // then app  finish();
                                setContentView(view);
                                timer = new Timer();

                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                view.invalidate();

                                            }
                                        });
                                    }
                                }, 0, 60);
                            }
                        });

        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box

        alertDialog.show();
    }


}
