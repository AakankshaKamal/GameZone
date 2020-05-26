package com.example.gamezone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Spalsh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        ImageView img=findViewById(R.id.icon);
        //ImageView img = (ImageView)findViewById(R.id.imgvw);
        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeanim_in);
        img.startAnimation(aniFade);
        Thread thread=new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(new Intent(Spalsh.this,MainActivity.class));

                }
            }
        };thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
