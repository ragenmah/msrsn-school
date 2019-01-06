package com.example.ragenmah99.msrsnproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;



public class Books extends AppCompatActivity {
    Button btncomputer,btncivil,btnelex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_layout);
        // Showing and Enabling clicks on the Home/Up button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btncomputer= (Button) findViewById(R.id.btn_computer);
        btncivil= (Button) findViewById(R.id.btn_civil);
        btnelex= (Button) findViewById(R.id.btn_elex);

        btncomputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Books.this , ComputerEng_notes.class);
                startActivity(intent1);
            }

        });
        btncivil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmesage("Maintainance Problem","Sorry!! This feature will be added soon...");

//                Intent intent1=new Intent(Notes.this , Civil_notes.class);
//                startActivity(intent1);
            }

        });
        btnelex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmesage("Maintainance Problem","Sorry!! This feature will be added soon...");
            }
//                Intent intent1=new Intent(Notes.this , ElexComm_notes.class);
//                startActivity(intent1);
//            }
//
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public void showmesage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
}