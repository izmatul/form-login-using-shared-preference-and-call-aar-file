package com.example.izmatulfarihah.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by izmatul.farihah on 29/09/2014.
 */
public class SessionManager {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Context context;
    Contact contact;
    DatabaseHandler db = new DatabaseHandler(context);

    int PRIVATE_MODE = 0;
    private static final String IS_LOGIN = "isLoggedIn";

    public SessionManager(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(db.TABLE_NAME, PRIVATE_MODE);
        editor = sp.edit();
    }

    //Create login session

    public void createLoginSession(Contact contact) {
        editor.putBoolean(IS_LOGIN, true );
        editor.putString(db.KEY_NAME, contact.getName());
        editor.putString(db.KEY_EMAIL, contact.getEmail());
        editor.putString(db.KEY_PH_NO, contact.getPhone_number());
//        Log.i("name", contact.getName());
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sp.getBoolean(IS_LOGIN, false);
    }

//    public void checkLogin() {
//        if(!this.isLoggedIn()) {
//            Intent i = new Intent(context, )
//        }
//    }
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String name = sp.getString(db.KEY_NAME, null);
//        user.put(KEY_NAME, name);
        user.put(db.KEY_NAME, sp.getString(db.KEY_NAME, null));
        user.put(db.KEY_EMAIL, sp.getString(db.KEY_EMAIL, null));
        user.put(db.KEY_PH_NO, sp.getString(db.KEY_PH_NO, null));
        Log.i("nama", name);
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();

    }


}
