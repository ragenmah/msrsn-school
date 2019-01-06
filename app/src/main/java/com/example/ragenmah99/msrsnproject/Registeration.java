package com.example.ragenmah99.msrsnproject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registeration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayAdapter<CharSequence> adapter;
    Button submit;
    EditText eFn, eLn, eMob, eEmail, eUserName, ePass1, ePass2;
    String toastmsg = "null";
    String fn, ln, course, mob, email, username, pass1, pass2,universityS,facultyS,programS,semester,gender,hashpasswprd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration_layout);
        if(!isConnected(Registeration.this)) buildDialog(Registeration.this).show();
        eFn = findViewById(R.id.fn);
        eLn = findViewById(R.id.ln);
        //eMob = findViewById(R.id.mob);
        eEmail = findViewById(R.id.email);
        //eUserName = findViewById(R.id.username);
        ePass1 = findViewById(R.id.password);
        ePass2 = findViewById(R.id.repassword);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitClicked(view);
            }
        });

        Spinner spinneruniversity = findViewById(R.id.universityID);
        Spinner spinnerfaculty = findViewById(R.id.facultyID);
        Spinner spinnerprogram = findViewById(R.id.programID);
        Spinner spinnersem = findViewById(R.id.semesterID);
        Spinner spinnergender=findViewById(R.id.genderID);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.university, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneruniversity.setAdapter(adapter);
        spinneruniversity.setOnItemSelectedListener(this);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.semesters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersem.setAdapter(adapter);
        spinnersem.setOnItemSelectedListener(this);


        adapter = ArrayAdapter.createFromResource(this,
                R.array.faculty, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfaculty.setAdapter(adapter);
        spinnerfaculty.setOnItemSelectedListener(this);


        adapter = ArrayAdapter.createFromResource(this,
                R.array.program, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerprogram.setAdapter(adapter);
        spinnerprogram.setOnItemSelectedListener(this);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergender.setAdapter(adapter);
        spinnergender.setOnItemSelectedListener(this);


    }
    public void submitClicked(View view) {
        if(!isConnected(Registeration.this)) buildDialog(Registeration.this).show();
        else {
            fn = eFn.getText().toString();
            ln = eLn.getText().toString();
            //mob = eMob.getText().toString();
            email = eEmail.getText().toString();
            //username = eUserName.getText().toString();
            pass1 = ePass1.getText().toString();
            pass2 = ePass2.getText().toString();
            if (validated()) {
                //Toast.makeText(this, "WELLCOME", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(Registeration.this, Register_btn_clicked.class);
                intent1.putExtra("FIRSTNAME", fn);
                intent1.putExtra("LNAME", ln);
                intent1.putExtra("EMAIL", email);
                intent1.putExtra("PWD", pass1);
                intent1.putExtra("UNIVERSITY", universityS);
                intent1.putExtra("SEM", semester);
                intent1.putExtra("FACULTY", facultyS);
                intent1.putExtra("PROGRAM", programS);
                intent1.putExtra("GENDER", gender);
                startActivity(intent1);
                finish();
            }
        }
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
        builder.setMessage("You need to have Mobile Data or wifi to access this. Please turn it on.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return builder;
    }
    private boolean validated() {
        if (fn.isEmpty() || ln.isEmpty()  || email.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            Toast.makeText(this, "ALL FIELDS ARE MANDATORY", Toast.LENGTH_SHORT).show();
            return false;
        }
         else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!pass2.equals(pass1)) {
//            hashpasswprd=hashCode()
            Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (((Spinner) adapterView).getId()) {
            case R.id.semesterID:
                semester = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.universityID:
                universityS = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.facultyID:
                facultyS = adapterView.getItemAtPosition(i).toString();
                break;

            case R.id.programID:
                programS = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.genderID:
                gender=adapterView.getItemAtPosition(i).toString();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
