package com.example.ragenmah99.msrsnproject;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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


public class Register extends AppCompatActivity {
EditText Uname,Password,Re_password,email;
String uname,pass,re_pass,emails;
Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        Uname=(EditText)findViewById(R.id.usernameID);
        Password=(EditText)findViewById(R.id.passwordID);
        Re_password=(EditText)findViewById(R.id.retype_passID);
        email=(EditText)findViewById(R.id.emailID);
        registerBtn= (Button) findViewById(R.id.btn_registerID);

  /*      uname=Uname.getText().toString();
        pass=Password.getText().toString();
        re_pass=Re_password.getText().toString();
        emails=email.getText().toString();
*/
        /*uname="Ragen";
        pass="AS";
        re_pass="SD";
        emails="SA";*/

        /*if (!pass.equals(re_pass)) {
            Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
        }*/
            registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Intent intent1=new Intent(Register.this , Register_btn_clicked.class);
                uname=Uname.getText().toString();
                pass=Password.getText().toString();
                re_pass=Re_password.getText().toString();
                emails=email.getText().toString();
                intent1.putExtra("USERNAME", uname);
                intent1.putExtra("PASSWORD",pass);
                intent1.putExtra("EMAIL",emails);

                /* Bundle extras=new Bundle();
                extras.putString("USERNAME",uname);
                //extras.putString("PASSWORD",pass);
                //extras.putString("EMAIL",emails);
                intent1.putExtras(extras);
    */            startActivity(intent1);
                finish();
                //Toast.makeText(Register.this,"Processing...",Toast.LENGTH_LONG).show();
            }

        });


    }

}