package com.example.navendu.rbstrack.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.navendu.rbstrack.Main2Activity;
import com.example.navendu.rbstrack.R;
import com.example.navendu.rbstrack.model.Auth;
import com.example.navendu.rbstrack.model.Contacts;
import com.example.navendu.rbstrack.model.Employee;
import com.example.navendu.rbstrack.model.cont;
import com.example.navendu.rbstrack.services.apiService;
import com.example.navendu.rbstrack.services.contactadapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ContactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    Retrofit retrofit;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<String> arr;

    ArrayList<Contacts> nav;
    contactadapter g;


    ListView hh;
    Context jj;
    String racf;
    Context f;

    String password;
    EditText e;
    EditText e1;
    ArrayAdapter<String> studentadapter;
    final String BASE_URL = "http://192.168.43.154:8081/";

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
View v;
    apiService api;
    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




         v= inflater.inflate(R.layout.fragment_contacts, container, false);

    //    getActivity().getSupportActionBar().setTitle("CONTACTS");


        f=v.getContext();

         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Intent i=  getActivity().getIntent();

        racf=i.getStringExtra("rr");

        password=i.getStringExtra("rrr");


        arr=new ArrayList<String>();

jj=v.getContext();

        hh = v.findViewById(R.id.recyclerView);
//

        nav=new ArrayList<Contacts>();
         api=retrofit.create(apiService.class);
        Call<Employee> call=api.getProfilehero(racf,password);


        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {



                Employee b=response.body();

                //console.log();


            String names= b.getContactnames();

            String con=b.getContacts();


            String[] sp=names.split(",");
            String[] sp1=con.split(",");
                for(int i=0;i<sp.length;++i)
                {


                    nav.add(new Contacts(sp[i],sp1[i]));



                }



                   // hh.setLayoutManager(new LinearLayoutManager(jj, LinearLayoutManager.VERTICAL, false));




                    g = new contactadapter(nav,jj);
                    hh.setAdapter(g);



                //vv.setText(b.getRacf()+"hello"+b.getContactnames()+""+b.getPhoneno());


            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {


                Toast.makeText(f, "not connecting sent.",
                        Toast.LENGTH_SHORT).show();

            }
        });


        Button sendBtn = (Button) v.findViewById(R.id.rr);

        sendBtn.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View view) {
                                           //         sendSMSMessage();





                                           Intent i = new Intent(getActivity().getApplicationContext(),add.class);

                                           i.putExtra("rr",racf);


                                           startActivity(i);


                                          // finish();



                                       }
                                   });






return v;

    }

//
//    protected void sendSMSMessage() {
//
//        if (ContextCompat.checkSelfPermission(this.getContext(),
//                Manifest.permission.SEND_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
//                    Manifest.permission.SEND_SMS)) {
//            } else {
//                ActivityCompat.requestPermissions(this.getActivity(),
//                        new String[]{Manifest.permission.SEND_SMS},
//                        MY_PERMISSIONS_REQUEST_SEND_SMS);
//            }
//        } else {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage("918447180858", null, "navendu will be on leave today", null, null);
//            Toast.makeText(getContext(), "SMS sent.",
//                    Toast.LENGTH_LONG).show();
//
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage("918447180858", null, "navendu will be on leave today", null, null);
//                    Toast.makeText(getContext(), "SMS sent.",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getContext(),
//                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//        }
//
//    }
}
