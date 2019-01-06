package com.example.ragenmah99.msrsnproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    final User user=new User(SplashScreen.this);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(progressBar.VISIBLE);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
          if(user.getName()!="" ){
//              SplashScreen.this.startActivity(new Intent(SplashScreen.this,MainActivity.class));
//              SplashScreen.this.finish();
        Intent intent=new Intent(SplashScreen.this,MainActivity.class);
              intent.putExtra("PASSWORD",user.getPassword());
              intent.putExtra("EMAIL",user.getName());
              startActivity(intent);
              finish();
          }
          else{

              SplashScreen.this.startActivity(new Intent(SplashScreen.this,Login.class));
              SplashScreen.this.finish();

          }
            }
        },4000);




    }



}
