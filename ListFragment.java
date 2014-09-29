package com.example.izmatulfarihah.myapplication.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.izmatulfarihah.myapplication.Contact;
import com.example.izmatulfarihah.myapplication.DatabaseHandler;
import com.example.izmatulfarihah.myapplication.SessionManager;
import com.example.izmatulfarihah.myapplication.adapter.Adapter;
import com.example.izmatulfarihah.myapplication.R;
import com.example.izmatulfarihah.myapplication.bean.ListViewBean;
import com.project.jatis.scpmobile.MainImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by izmatul.farihah on 17/09/2014.
 */
public class ListFragment extends Fragment {

    private Contact contact;
    String username, email, phone_number, password;
    SessionManager session;
    ListView lv;
    DatabaseHandler db;
//    ConstantVar var = new ConstantVar();
    String[] merk = {
            "Nike Airmax Tailwind 20",
            "Nike Airmax Tailwind 21",
            "Nike Airmax Tailwind 22",
            "Nike Airmax Tailwind 23",
            "Nike Airmax Tailwind 24",
            "Redknot Clarity 121",
            "Redknot Clarity 122",
            "Redknot Luz 10",
            "Redknot Luz 11",
    };

    String[] desc = {
            "Free ongkir n dus ori",
            "Free ongkir n dus ori",
            "Free ongkir n dus ori",
            "Free ongkir n dus ori",
            "Free ongkir n dus ori",
            "Free ongkir n dus ori",
            "Free ongkir n dus ori",
            "Free ongkir n dus ori",
            "Free ongkir n dus ori",
    };

    String[] imageId = {
            "nike_airmax20",
            "nike_airmax21",
            "nike_airmax22",
            "nike_airmax23",
            "nike_airmax24",
            "rk_clarity121",
            "rk_clarity122",
            "rk_luz10",
            "rk_luz11",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_layout, container, false);
        super.onCreate(savedInstanceState);

        List<ListViewBean> listViewBeans = new ArrayList<ListViewBean>();
        for (int i=0; i<imageId.length; i++){
            String newMerk = merk[i];
            String newDesc = desc[i];
            String newImageId = imageId[i];
            ListViewBean listViewBean = new ListViewBean();
            listViewBean.setMerk(newMerk);
            listViewBean.setDesc(newDesc);
            listViewBean.setImage(newImageId);

            listViewBeans.add(listViewBean);
        }


        lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(new Adapter(getActivity(), R.layout.custom_listview, listViewBeans));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + merk[+position], Toast.LENGTH_SHORT).show();
                session = new SessionManager(getActivity());
                HashMap<String, String> user = session.getUserDetails();
                username = user.get(db.KEY_NAME);
                email = user.get(db.KEY_EMAIL);
                phone_number = user.get(db.KEY_PH_NO);
                session.logoutUser();
                Intent i = new Intent(getActivity(), MainImpl.class);
                i.putExtra("message", toJson(username, email, phone_number));
                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(i);
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//
//                OrderFragment orderFragment = new OrderFragment();
//                ft.replace(R.id.content_frame, orderFragment);
//                ft.addToBackStack(null);
//                ft.commit();


            }
        });

        return view;
    }
    public void getData(Contact contact) {
        username = contact.getName();
        email = contact.getEmail();
        phone_number = contact.getPhone_number();
        password = contact.getPassword();
//        toJson(username, email, phone_number);
    }

    public static String toJson(String username, String email, String phone_number) {
        JSONObject json =  new JSONObject();
        try {
            json.put("name", username);
            json.put("email", email);
            json.put("phone_number", phone_number);
//            json.put("color", "pink");
            return json.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


}
