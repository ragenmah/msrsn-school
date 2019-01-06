package com.example.ragenmah99.msrsnproject;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class  Contact_main  extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Contact_Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Album> albumList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_item_layout);

        //Create model list
        albumList=new ArrayList<>();
        albumList.add(new Album("MILAN ADHIKARI","adhirakrimilan604@gmail.com",R.drawable.milan,"CEO/Founder"));
        albumList.add(new Album("SAHAS DANGOL","dangolsahas@gmail.com",R.drawable.sahas,"CEO/Founder"));
        albumList.add(new Album("RAGEN MAHARJAN","ragenmah99@gmail.com",R.drawable.ragen,"Android Developer"));
        albumList.add(new Album("RAJESH BDR. SHRESTHA","rajeshshrestha555@gmail.com",R.drawable.rajesh,"Back end developer"));
        albumList.add(new Album("BIRAJ KHATRI","biraz.khatry@gmail.com",R.drawable.biraz,"Front end developer"));
        albumList.add(new Album("NABIN BIKRAM SAH","snabinbikram@gmail.com",R.drawable.nabin,"Database manager"));
        albumList.add(new Album("SAMUNDRA NEUPANE","samundraneupane123@yahoo.com",R.drawable.samundra,"Sponser"));
        albumList.add(new Album("POOJA LOHANI","",R.drawable.pooja,"General Admin"));
        albumList.add(new Album("ROZEENA HENGAJU","",R.drawable.rozeena,"General Admin"));

        //Bind recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //Indicate a layoutManager
        //It shows items in a vertical or horizontal scrolling list.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create an adapter
        adapter = new Contact_Adapter(Contact_main.this,albumList);

        //Create custom interface object and send it to adapter
        //Adapter trigger it when any item view is clicked
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Set adapter to recyclerview
        recyclerView.setAdapter(adapter);
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


}
