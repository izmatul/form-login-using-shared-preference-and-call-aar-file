package com.example.izmatulfarihah.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by izmatul.farihah on 25/09/2014.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 22;
    private static final String DATABASE_NAME = "peopleData";
    public static final String TABLE_NAME = "user_data";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PH_NO = "phone_number";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASS = "password";

    public DatabaseHandler(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT NOT NULL,"
                + KEY_EMAIL + " TEXT NOT NULL,"
                + KEY_PH_NO + " TEXT NOT NULL,"
                + KEY_PASS + " TEXT NOT NULL" + ")";
        Log.i("create", CREATE_CONTACTS_TABLE );
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.i("old", String.valueOf(oldVersion));
        Log.i("new", String.valueOf(newVersion));
        // Create tables again
        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_PH_NO, contact.getPhone_number());
        values.put(KEY_PASS, contact.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Getting all contacts
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<Contact>();
        String selectQuery = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setEmail(cursor.getString(2));
                contact.setPhone_number(cursor.getString(3));
                contact.setPassword(cursor.getString(4));
                contacts.add(contact);
            } while (cursor.moveToNext());
        }


        return contacts;
    }

    public Contact getContact(String email, String password) {
        Contact contact = new Contact();
        String selectQuery = "select * from " + TABLE_NAME
                + " where " + KEY_EMAIL + " = '" + email
                + "' and " + KEY_PASS + " = '" + password +"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setEmail(cursor.getString(2));
            contact.setPhone_number(cursor.getString(3));
            contact.setPassword(cursor.getString(4));
            cursor.close();
        }
        else
            contact = null;
        return contact;
    }

    //Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_PH_NO, contact.getPhone_number());
        values.put(KEY_PASS, contact.getPassword());

        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] {String.valueOf(contact.getId())});
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
