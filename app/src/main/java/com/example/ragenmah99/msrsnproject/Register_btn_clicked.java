package com.example.ragenmah99.msrsnproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class Register_btn_clicked extends AppCompatActivity {

    TextView displayName;
    String fname, lname, course, mob, email, passwd,universityS,facultyS,programS,semester,emailfromserver,gender;
    ProgressBar spinner;
    Button loginfrmRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_clicked_layout);
  /*      Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Uname = extras.getString("USERNAME");
        email = extras.getString("EMAIL");
*/
        fname=getIntent().getExtras().getString("FIRSTNAME");;
        lname=getIntent().getExtras().getString("LNAME");;
        universityS=getIntent().getExtras().getString("UNIVERSITY");;
        semester=getIntent().getExtras().getString("SEM");;
        facultyS=getIntent().getExtras().getString("FACULTY");;
        programS=getIntent().getExtras().getString("PROGRAM");;
        passwd=getIntent().getExtras().getString("PWD");;
        //Re_password=getIntent().getExtras().getString("med_name");;
        email=getIntent().getExtras().getString("EMAIL");
        gender=getIntent().getExtras().getString("GENDER");

        loginfrmRegister=(Button)findViewById(R.id.btn_login_hereID);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        displayName = (TextView)findViewById(R.id.displayName);
        displayName.setText(fname);


      //   new fetchData().execute();
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

                URL url = new URL("https://www.msrsn.com/android/register_ragen.php?fname="+fname+"&lname="+lname+"&email="+email+"&password="+passwd+"&university="+universityS+"&faculty="+facultyS+"&semester="+semester+"&program="+programS+"&gender="+gender);
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
                    //goto log in if get response
                    loginfrmRegister.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            Intent intent1=new Intent(Register_btn_clicked.this , Login.class);
                            startActivity(intent1);
                            finish();
                            //Toast.makeText(Register.this,"Processing...",Toast.LENGTH_LONG).show();
                        }

                    });
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

            }
            try
            {
                //JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                JSONArray list = new JSONArray(response);


                for( int i = 0 ; i < list.length() ; i++)
                {

                    JSONObject JO = list.getJSONObject(i);
                    emailfromserver=JO.getString("email");
                    if(email.equals(emailfromserver)){
                        //Toast.makeText(Register_btn_clicked.this,"fuck you...",Toast.LENGTH_LONG).show();
                        showmesage("Email allready registered","Please enter different email");
                    }
                    else{/*
                        AlertDialog.Builder builder=new AlertDialog.Builder(Register_btn_clicked.this);
                        builder.setCancelable(true);
                        builder.setTitle("Thanks!!");
                        builder.setMessage("Thanks for registeration");
                        builder.setPositiveButton("OK", null);
                        builder.show();*/
                        //   showmesage("Email a","Please enter different email");

                    }

                }


                if ( list.length() == 0)
                {

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
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent1=new Intent(Register_btn_clicked.this , Registeration.class);
                startActivity(intent1);
                finish();
            }
        });
        builder.show();

    }
}
//    class fetchData extends AsyncTask<Void,Void,Void> {
//        String data = "";
//        String dataParsed = "";
//        String singleParsed = "", uname,pass,emails;
//
//        @Override
//        protected void onPreExecute() {
//            //Toast.makeText(Register_btn_clicked.this,"Processing...",Toast.LENGTH_LONG).show();
//            spinner.setVisibility(View.VISIBLE);
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try {
//                //URL url = new URL("https://api.myjson.com/bins/j5f6b");
//                // https://school-msrsn.000webhostapp.com/login_ragen.php?email=%27eklosahas@gmail.com%27
//                //URL url = new URL("https://ourproject071.000webhostapp.com/register_ragen.php?name=" + uname);
//                //String urlname="https://ourproject071.000webhostapp.com/register_ragen.php?name=+Uname+&email=rage@gmail.com&password=a";
//
////                URL url = new URL("https://ourproject071.000webhostapp.com/register_ragen.php?name="+Uname+"&email="+email+"&password="+Password);
//                URL url = new URL("https://www.msrsn.com/android/register_ragen.php?fname="+fname+"&lname="+lname+"&email="+email+"&password="+passwd+"&university="+universityS+"&faculty="+facultyS+"&semester="+semester+"&program="+programS+"&gender="+gender);
//
//               // "https://ourproject071.000webhostapp.com/register_ragen.php?fname="+fname+"&lname="+lname+"&email="+email+"&password="+passwd+"&university="+universityS+&faculty=sc&semester=sem&program=hell
//
//                //"https://ourproject071.000webhostapp.com/register_ragen.php?name="+uname+"&email="+emails+"&password="+pass
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                String line = "";
//                while (line != null) {
//                    line = bufferedReader.readLine();
//                    data = data + line;
//                }
//                //websearching();
//                JSONArray JA = new JSONArray(data);
//                for (int i = 0; i < JA.length(); i++) {
//                    //JSONObject JO = (JSONObject) JA.get(i);
//                /*singleParsed =  "Name:" + JO.get("name") + "\n"+
//                                "Password:" + JO.get("password") + "\n"+
//                                "Contact:" + JO.get("contact") + "\n"+
//                                "Country:" + JO.get("country") + "\n";*/
//
//                   JSONObject JO = (JSONObject) JA.get(i);
//
//                    uname=JO.getString("username");
//                    pass=JO.getString("password");
//                    emails=JO.getString("email");
//                    //dataParsed = dataParsed + singleParsed +"\n" ;
//                    // dataParsed = dataParsed + singleParsed + "\n" + MedName + "\n" + Usage + "\n" + Dosage + "\n" + SideEffect + "\n" + name + "\n";
//                    /*boolean isInserted=myDbHelper.insertData(name,Usage,Dosage,SideEffect);
//                    if(isInserted)
//                        Toast.makeText(websearch_activity.this,"Data Inserted",Toast.LENGTH_LONG).show();
//                    else
//                        Toast.makeText(websearch_activity.this,"Data not Inserted",Toast.LENGTH_LONG).show();*/
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            // myDbHelper.insertData(name, Usage, Dosage, SideEffect);
//            // MainActivity.data.setText(this.dataParsed);
//            //webview
//
//            // boolean isInserted=myDbHelper.insertData(name,Usage,Dosage,SideEffect);
//            //if(isInserted)
//           // Toast.makeText(Register_btn_clicked.this,"Registering...",Toast.LENGTH_LONG).show();
//            spinner.setVisibility(INVISIBLE);
//          /*  else
//                Toast.makeText(websearch_activity.this,"Medicine not Inserted",Toast.LENGTH_LONG).show();
//        */}
//
//    }

