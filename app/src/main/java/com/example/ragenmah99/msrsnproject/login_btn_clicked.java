package com.example.ragenmah99.msrsnproject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class login_btn_clicked extends AppCompatActivity {
    TextView displayName;
    String fname, lname, course, mob, email, passwd,universityS,facultyS,programS,semester,emailSe,passwordS;
    ProgressBar spinner;
    Button getstartedBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_btn_clicked);


        getstartedBtn=(Button)findViewById(R.id.btn_login_hereID);

        passwd=getIntent().getExtras().getString("PASSWORD");;
        //Re_password=getIntent().getExtras().getString("med_name");;
        email=getIntent().getExtras().getString("EMAIL");;


        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        displayName = (TextView)findViewById(R.id.displayName);


        //new fetchData().execute();
        new runAPI().execute();
    }





    class runAPI extends AsyncTask<Void,Void, String>
    {


        // private Exception exception;
        protected void onPreExecute() //what to do after request is sent and before the data arrives
        {
            // responseDisplay.setText("Not executed anything");
            //save(medicine);

            spinner.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(Void... urls) //what to do when data has arrived
        {

            try
            {

                URL url = new URL("https://www.msrsn.com/android/login_ragen.php?email="+email+"&password="+passwd);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try
                {
                    BufferedReader bufferedReader = new BufferedReader ( new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine())!= null)
                    {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally
                {
                    urlConnection.disconnect();
                }
            }
            catch (Exception e)
            {
                return null;
            }

            // spinner.setVisibility(View.GONE);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected void onPostExecute(String response) //what to do after data has been collected
        {

            spinner.setVisibility(INVISIBLE);
            if ( response == null)
            {
              /*response = "There no medicine with such names";
                responseDisplay.setText(response);
                return;*/

                showmesage("Something went wrong","Check your email or password");
                new User(login_btn_clicked.this).removeUser();
                new User(login_btn_clicked.this).removePassword();

            }
            try
            {
                //JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                JSONArray list = new JSONArray(response);


                for( int i = 0 ; i < list.length() ; i++)
                {

                    JSONObject JO = list.getJSONObject(i);

                    fname=JO.getString("fname");
                    lname=JO.getString("lname");
                    emailSe=JO.getString("email");
                    passwordS=JO.getString("password");
                    universityS=JO.getString("university");
                    facultyS=JO.getString("faculty");
                    programS=JO.getString("program");
                    semester=JO.getString("semester");
                    displayName.setText(fname+" "+lname);
                }

                getstartedBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent1=new Intent(login_btn_clicked.this , MainActivity.class);
                        startActivity(intent1);
                        finish();
                        //Toast.makeText(Register.this,"Processing...",Toast.LENGTH_LONG).show();
                    }

                });


                if ( list.length() == 0)
                {

                    showmesage("Something went wrong","Check your email or password");

                    new User(login_btn_clicked.this).removeUser();
                    new User(login_btn_clicked.this).removePassword();
                }

            }catch (JSONException e)
            {
                e.printStackTrace();
            }
            spinner.setVisibility(INVISIBLE);

        }
        // spinner.setVisibility(View.INVISIBLE)

    }
    public void showmesage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent1=new Intent(login_btn_clicked.this , Login.class);
                startActivity(intent1);
                finish();
            }
        });
//        builder.setCanceledOnTouchOutside(false);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
//        return dialog;
        builder.show();


    }
//    public static void showAlert(Context context, String title, String text) {
//        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setTitle(title);
//        alertDialog.setMessage(text);
//        alertDialog.setCanceledOnTouchOutside(true);
//        alertDialog.show();
//    }
}
