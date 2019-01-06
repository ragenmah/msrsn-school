package com.example.ragenmah99.msrsnproject;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class First_Sem_Computer extends AppCompatActivity  {
    DrawerLayout drawer;
    private ActionBarDrawerToggle mToggle;
    private Spinner s1,s2;
    Button b1;
    String[] state=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_sem_main);
        // Showing and Enabling clicks on the Home/Up button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer =(DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       s1=(Spinner)findViewById(R.id.spinner1);
        s2=(Spinner)findViewById(R.id.spinner2);

        //b1=(Button)findViewById(R.id.btnSearch);

      /* s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    state=new String[]{"Sub1","Sub2"};

                }
                if(position==1){
                    state=new String[]{"Sub3","Sub4"};

                }
                ArrayAdapter<String> adt = new ArrayAdapter<String>(First_Sem_Computer.this, android.R.layout.simple_spinner_item);
                s2.setAdapter(adt);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return  true;
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
        if (id == R.id.search) {
            if(drawer.isDrawerOpen(Gravity.END))
                drawer.closeDrawer(Gravity.START);
            else
                drawer.openDrawer(Gravity.END);

        }

        return super.onOptionsItemSelected(item);
    }


}
