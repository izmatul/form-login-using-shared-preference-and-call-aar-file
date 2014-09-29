package com.example.izmatulfarihah.myapplication.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.izmatulfarihah.myapplication.Contact;
import com.example.izmatulfarihah.myapplication.DatabaseHandler;
import com.example.izmatulfarihah.myapplication.R;
import java.util.List;

/**
 * Created by izmatul.farihah on 17/09/2014.
 */
public class RegFragment extends Fragment implements View.OnClickListener{
    private DatabaseHandler db;
    private Contact contact;
    EditText reg_username, reg_email, reg_phone_number, reg_password;
    Button btnRegister;
    String username, email, phone_number, password;
    ListFragment listFragment = new ListFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register, container, false);
        db = new DatabaseHandler(getActivity());

        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        reg_username = (EditText) view.findViewById(R.id.reg_username);
        reg_email = (EditText) view.findViewById(R.id.reg_email);
        reg_phone_number = (EditText) view.findViewById(R.id.reg_phone_number);
        reg_password = (EditText) view.findViewById(R.id.reg_password);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==btnRegister) {

            username = String.valueOf(reg_username.getText());
            email = String.valueOf(reg_email.getText());
            phone_number = String.valueOf(reg_phone_number.getText());
            password = String.valueOf(reg_password.getText());

            reg_username.setText("");
            reg_email.setText("");
            reg_phone_number.setText("");
            reg_password.setText("");

            Contact contact = new Contact(username, email, phone_number, password);
            db.addContact(contact);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            listFragment.getData(contact);
            ft.replace(R.id.content_frame, listFragment);
            ft.addToBackStack(null);
            ft.commit();
            Log.i("data", contact.getEmail());

            Log.d("Reading: ", "Reading all contacts..");
            List<Contact> contacts = db.getAllContacts();
            for (Contact cn : contacts) {
                String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Email: " + cn.getEmail() + " ,Phone: " + cn.getPhone_number() + " ,Password: " + cn.getPassword();
                // Writing Contacts to log
                Log.d("Name: ", log);
            }
        }
    }
}
