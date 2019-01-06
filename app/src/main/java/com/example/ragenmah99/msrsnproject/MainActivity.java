package com.example.ragenmah99.msrsnproject;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CardView mycard,c2,c3,c4,c5,c6;
    Intent i1,i2,i3,i4,i5,i6 ;
    LinearLayout ll;
    ListView lv;
    EditText messageEditText,nameEditText,emailEditText;
    Button sendBtn;

    final Boolean forUpdate=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ll = (LinearLayout) findViewById(R.id.ll);
        mycard = (CardView) findViewById(R.id.noteID);
        i1 = new Intent(this,Notes.class);
        //i1 = new Intent(this,DisplayMedicine.class);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i1);
            }
        });
        //mycard.setBackgroundResource(R.drawable.cerclebackgroundpink);
        c2=(CardView) findViewById(R.id.questionID);
        i2=new Intent(this,OldQuestions.class);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i2);
            }
        });

        c3=(CardView) findViewById(R.id.syllabusID);
        i3=new Intent(this,Syllabus.class);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i3);
            }
        });

        c4=(CardView) findViewById(R.id.booksID);
//        i4=new Intent(this,Books.class);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(i4);
                showmesage("Maintainance Problem","Sorry!! This feature will be added soon...");

            }
        });

        c5=(CardView) findViewById(R.id.contactID);
        i5=new Intent(this,Contact_main.class);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i5);
            }
        });

        c6=(CardView) findViewById(R.id.miscID);
        i6=new Intent(this,Misc_main.class);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(i6);
//    showmesage("Maintainance Problem","Sorry!! This feature will be added soon...");

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog(false);
            }
        });


        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {}
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    private void displayDialog(Boolean forUpdate)
    {
        Dialog d=new Dialog(this);
        d.setTitle("Send Message");
        d.setContentView(R.layout.contact_sms_layout);

        nameEditText= (EditText) d.findViewById(R.id.nameEditTxt);
        emailEditText= (EditText) d.findViewById(R.id.emailEditTxt);
        messageEditText= (EditText) d.findViewById(R.id.messageEditTxt);
        sendBtn= (Button) d.findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent1=new Intent(MainActivity.this , Notes.class);
//                startActivity(intent1);
                // Message to be sent by mailspan>

                String message = messageEditText.getText().toString();
                String emailId=emailEditText.getText().toString();
                // Subject to mail
                String name = nameEditText.getText().toString();

                // To whom mail will be sent
//                String to = kime.getText().toString();
                String to = "msrsn123@gmail.com";


                //Lets check

                if (message.isEmpty() || name.isEmpty() || to.isEmpty()||emailId.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Fields Are Mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setType("message/rfc822");
                    email.putExtra(Intent.EXTRA_EMAIL,emailId);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    email.putExtra(Intent.EXTRA_SUBJECT, name);
                    email.putExtra(Intent.EXTRA_TEXT, message);

                    // Let's look at an application that will send
                    try {
                        startActivity(Intent.createChooser(email, "Send Email Using  "));

                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Email is not Sent", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


  d.show();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_logout) {
////            Intent intent1=new Intent(MainActivity.this , Login.class);
////            startActivity(intent1);
////            finish();
//////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notes) {
            Intent intent1=new Intent(MainActivity.this , Notes.class);
            startActivity(intent1);
        } else if (id == R.id.nav_old_questions) {
            Intent intent2=new Intent(MainActivity.this , OldQuestions.class);
            startActivity(intent2);
        } else if (id == R.id.nav_syllabus) {
            Intent intent3=new Intent(MainActivity.this , Syllabus.class);
            startActivity(intent3);
        } else if (id == R.id.nav_books) {
            showmesage("Maintainance Problem","Sorry!! This feature will be added soon...");

//            Intent intent4=new Intent(MainActivity.this , Books.class);
//            startActivity(intent4);
        } else if (id == R.id.nav_contact) {
            Intent intent5=new Intent(MainActivity.this , Contact_main.class);
            startActivity(intent5);
        } else if(id==R.id.nav_logout){
            new User(MainActivity.this).removeUser();
            new User(MainActivity.this).removePassword();

            Intent intent6=new Intent(MainActivity.this , Login.class);
            startActivity(intent6);
            finish();
        }else if(id==R.id.nav_shareit){
             Intent myIntent =new Intent(Intent.ACTION_SEND);
             myIntent.setType("Text/plain");
             String shareBody="I recommend you to use MSRSN App.";
             String shareSubject="ABOUT MSRSN APP";
             String weblink="www.msrsn.com";
             myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
             myIntent.putExtra(Intent.EXTRA_TEXT,shareBody+"Visit our website: "+weblink);
//             myIntent.putExtra(Intent.EXTRA_TEXT, );
             startActivity(Intent.createChooser(myIntent,"Share Via..."));
        }else if (id == R.id.nav_misc) {
            Intent intent3=new Intent(MainActivity.this , Misc_main.class);
            startActivity(intent3);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }
    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
}
