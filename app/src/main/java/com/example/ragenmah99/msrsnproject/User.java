package com.example.ragenmah99.msrsnproject;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    Context context;

     public void removeUser(){
    sharedPreferences.edit().clear().commit();
     }

    public String getName() {
        name=sharedPreferences.getString("userdata","");
        return name;
    }

    public void setName(String name) {
        this.name = name;
       sharedPreferences.edit().putString("userdata",name).commit();
    }

     private String name,password;
     SharedPreferences sharedPreferences,sharedPreferences2;
    public User(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        sharedPreferences2=context.getSharedPreferences("password",Context.MODE_PRIVATE);
    }
    public void removePassword(){
        sharedPreferences2.edit().clear().commit();
    }

    public String getPassword() {
        password=sharedPreferences2.getString("password","");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        sharedPreferences2.edit().putString("password",name).commit();
    }
}
