package com.example.ragenmah99.msrsnproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;



public class ComputerEng_questions extends AppCompatActivity {
    CardView mycard,c2,c3,c4,c5,c6,c7,c8;
    Intent i1,i2,i3,i4,i5,i6,i7,i8 ;
    LinearLayout ll;
    String s1,s2,s3,s4,s5,s6,s7,s8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semester_layout);
        // Showing and Enabling clicks on the Home/Up button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ll = (LinearLayout) findViewById(R.id.ll);


        s1="1";s2="2";s3="3";s4="4";s5="5";s6="6";s7="7";s8="8";
        mycard = (CardView) findViewById(R.id.First_semID);
//        i1.putExtra("s1",s1);
//        mycard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent i = new Intent (view.getContext(),Notes_main.class);
// //                i.putExtra("s1",s1);
//
//                view.getContext().startActivity(i);
//            }
//        });

//        i1 = new Intent(ComputerEng_notes.this,Notes_main.class);
        i1 = new Intent(ComputerEng_questions.this,semester1oldquestion.class);

        //i1 = new Intent(this,DisplayMedicine.class);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i1);
            }
        });
        //mycard.setBackgroundResource(R.drawable.cerclebackgroundpink);
        c2=(CardView) findViewById(R.id.second_semID);
        i2=new Intent(this,semester2oldquestion.class);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i2);
            }
        });

        c3=(CardView) findViewById(R.id.third_semID);
        i3=new Intent(this,semester3oldquestion.class);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i3);
            }
        });

        c4=(CardView) findViewById(R.id.Fourth_semID);
        i4=new Intent(this,semester4oldquestion.class);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i4);
            }
        });

        c5=(CardView) findViewById(R.id.fifth_semID);
        i5=new Intent(this,semester5oldquestion.class);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i5);
            }
        });

        c6=(CardView) findViewById(R.id.Sixth_semID);
        i6=new Intent(this,semester6oldquestion.class);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i6);
            }
        });

        c7=(CardView) findViewById(R.id.Seventh_SemID);
        i7=new Intent(this,semester7oldquestion.class);
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i7);
            }
        });


        c8=(CardView) findViewById(R.id.Eight_semID);
        i8=new Intent(this,semester8oldquestion.class);
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i8);
            }
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
}