package com.example.ragenmah99.msrsnproject;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.view.View.INVISIBLE;


public class Login extends AppCompatActivity {
    EditText PasswordText,emailtext;
    String passwordS,emailS;
    String fname, lname, course, mob, email, passwd,universityS,facultyS,programS,semester;
    Button loginBtn,registerfrmthis,forgetpass;


    NetworkInfo wifiCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        emailtext=(EditText)findViewById(R.id.email_loginID);
        PasswordText=(EditText)findViewById(R.id.password_loginID);
        loginBtn= (Button) findViewById(R.id.btn_loginID);
        //new runAPI().execute();
//        ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        wifiCheck = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


//        if(!isConnected(Login.this)) buildDialog(Login.this).show();
//        else {
////            Toast.makeText(Login.this,"Welcome", Toast.LENGTH_SHORT).show();
////            setContentView(R.layout.activity_main);
//        }

        if(!isConnected(Login.this)) buildDialog(Login.this).show();
        else {}
                loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtnClicked(view);
            }
        });
        forgetpass=(Button) findViewById(R.id.forget_passID);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showmesage("Sorry!!","Please visit our website www.msrsn.com ...");

//                finish();
            }

        });
        registerfrmthis=(Button) findViewById(R.id.register_frm_loginID);
        registerfrmthis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1=new Intent(Login.this , Registeration.class);

                startActivity(intent1);
//                finish();
            }

        });

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean isFirst = getPrefs.getBoolean("firstStart",true);

                if(isFirst)
                {
                    startActivity(new Intent(Login.this,MyIntro.class));
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("firstStart",false);
                    e.apply();

                }

            }
        });
        thread.start();



    }
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
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
    public void loginBtnClicked(View view) {

        if(!isConnected(Login.this)) buildDialog(Login.this).show();
        else {
            passwordS=PasswordText.getText().toString();
            emailS=emailtext.getText().toString();

            if (validated()) {
                //Toast.makeText(this, "WELLCOME", Toast.LENGTH_SHORT).show();
                User user=new User(Login.this);
                user.setName(emailS);
                user.setPassword(passwordS);
                Intent intent1=new Intent(Login.this , login_btn_clicked.class);
                intent1.putExtra("PASSWORD",passwordS);
                intent1.putExtra("EMAIL",emailS);


                startActivity(intent1);
                finish();
            }
        }


    }

    private boolean validated() {

        if (passwordS.isEmpty()   || emailS.isEmpty()) {
            Toast.makeText(this, "ALL FIELDS ARE MANDATORY", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailS).matches()) {
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            return false;
        }
         else {
            return true;
        }

    }
//    class runAPI extends AsyncTask<Void,Void, String>
//    {
//
//
//        // private Exception exception;
//        protected void onPreExecute() //what to do after request is sent and before the data arrives
//        {
//            // responseDisplay.setText("Not executed anything");
//            //save(medicine);
//          //  spinner.setVisibility(View.VISIBLE);
//        }
//
//        protected String doInBackground(Void... urls) //what to do when data has arrived
//        {
//
//            try
//            {
//
//                URL url = new URL("https://www.msrsn.com/android/login_ragen.php?email="+email);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                try
//                {
//                    BufferedReader bufferedReader = new BufferedReader ( new InputStreamReader(urlConnection.getInputStream()));
//                    StringBuilder stringBuilder = new StringBuilder();
//                    String line;
//                    while ((line = bufferedReader.readLine())!= null)
//                    {
//                        stringBuilder.append(line).append("\n");
//                    }
//                    bufferedReader.close();
//                    return stringBuilder.toString();
//                }
//                finally
//                {
//                    urlConnection.disconnect();
//                }
//            }
//            catch (Exception e)
//            {
//                return null;
//            }
//
//            // spinner.setVisibility(View.GONE);
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//        protected void onPostExecute(String response) //what to do after data has been collected
//        {
//
//           // spinner.setVisibility(INVISIBLE);//
//            if ( response == null)
//            {
//              /*response = "There no medicine with such names";
//                responseDisplay.setText(response);
//                return;*/
//                Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//
//            }
//            try
//            {
//                //JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                JSONArray list = new JSONArray(response);
//                if ( list.length() == 0)
//                {
//                  /* response = "There no medicine with such names";
//                    responseDisplay.setText(response);//directing to next page
//                    return;*/
//                    Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//
//                for( int i = 0 ; i < list.length() ; i++)
//                {
//
//                    JSONObject JO = list.getJSONObject(i);
//
//                    fname=JO.getString("fname");
//                    lname=JO.getString("lname");
//                    email=JO.getString("email");
//                    passwd=JO.getString("password");
//                    universityS=JO.getString("unversity");
//                    facultyS=JO.getString("faculty");
//                    programS=JO.getString("program");
//                    semester=JO.getString("semester");
//                   // displayName.setText(fname);
//                }
//
//            }catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//          //  spinner.setVisibility(INVISIBLE);
//
//        }
//        // spinner.setVisibility(View.INVISIBLE)
//
//    }

}