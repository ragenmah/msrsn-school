package com.example.ragenmah99.msrsnproject;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;



public class Home extends AppCompatActivity {
    CardView mycard,c2,c3,c4,c5,c6;
    Intent i1,i2,i3,i4,i5,i6 ;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        ll = (LinearLayout) findViewById(R.id.ll);
        mycard = (CardView) findViewById(R.id.noteID);
        i1 = new Intent(this,Registeration.class);
        //i1 = new Intent(this,DisplayMedicine.class);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i1);
            }
        });
        //mycard.setBackgroundResource(R.drawable.cerclebackgroundpink);
        c2=(CardView) findViewById(R.id.questionID);
        i2=new Intent(this,Contact.class);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i2);
            }
        });

        c3=(CardView) findViewById(R.id.syllabusID);
        i3=new Intent(this,Registeration.class);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i3);
            }
        });

        c4=(CardView) findViewById(R.id.booksID);
        i4=new Intent(this,Register.class);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i4);
            }
        });

        c5=(CardView) findViewById(R.id.contactID);
        i5=new Intent(this,Login.class);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i5);
            }
        });

        c6=(CardView) findViewById(R.id.miscID);
        i6=new Intent(this,semester1notes.class);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i6);
            }
        });


    }
}