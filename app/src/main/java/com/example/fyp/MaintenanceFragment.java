package com.example.fyp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceFragment extends Fragment {

    Spinner Time, DayDate, Month;
    Button Submit;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View myview = inflater.inflate (R.layout.fragment_maintenance, container, false);

        Time=myview.findViewById(R.id.time);
        DayDate=myview.findViewById(R.id.daydate);
        Month=myview.findViewById(R.id.month);
        Submit=myview.findViewById (R.id.maintain);

        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=auth.getCurrentUser();
        final String id=firebaseUser.getUid();
        reference=database.getInstance ().getReference ().child ("Users").child (id);

        //time
        final List<String> time=new ArrayList<> ();
        time.add ("Choose time");
        time.add ("10 am - 11 am");
        time.add ("11 am - 12 pm");
        time.add ("12 pm - 1 pm");
        time.add ("5 pm - 6 pm");
        time.add ("6 pm - 7 pm");
        time.add ("7 pm - 8 pm");
        time.add ("8 pm - 9 pm");

        ArrayAdapter<String>dataAdapter;
        dataAdapter=new ArrayAdapter (getActivity ().getApplicationContext(),android.R.layout.simple_spinner_item,time);
        dataAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

        Time.setAdapter (dataAdapter);

        Time.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition (i).equals ("Choose time")){
            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //date
        final List<String> date=new ArrayList<> ();
        date.add ("Choose date");
        date.add ("1");
        date.add ("2");
        date.add ("3");
        date.add ("4");
        date.add ("5");
        date.add ("6");
        date.add ("7");
        date.add ("8");
        date.add ("9");
        date.add ("10");
        date.add ("11");
        date.add ("12");
        date.add ("13");
        date.add ("14");
        date.add ("15");
        date.add ("16");
        date.add ("17");
        date.add ("18");
        date.add ("19");
        date.add ("20");
        date.add ("21");
        date.add ("22");
        date.add ("23");
        date.add ("24");
        date.add ("25");
        date.add ("26");
        date.add ("27");
        date.add ("28");
        date.add ("29");
        date.add ("30");
        date.add ("31");

        ArrayAdapter<String>dateAdapter;
        dateAdapter=new ArrayAdapter (getActivity ().getApplicationContext(),android.R.layout.simple_spinner_item,date);
        dateAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

        DayDate.setAdapter (dateAdapter);

        DayDate.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition (i).equals ("Choose date")){
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //month
        final List<String> month=new ArrayList<> ();
        month.add ("Choose month");
        month.add ("January");
        month.add ("February");
        month.add ("March");
        month.add ("April");
        month.add ("May");
        month.add ("June");
        month.add ("July");
        month.add ("August");
        month.add ("September");
        month.add ("October");
        month.add ("November");
        month.add ("December");

        ArrayAdapter<String>monthAdapter;
        monthAdapter=new ArrayAdapter (getActivity ().getApplicationContext(),android.R.layout.simple_spinner_item,month);
        monthAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

        Month.setAdapter (monthAdapter);

        Month.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition (i).equals ("Choose month")){
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        reference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child("daydate").setValue(DayDate.getSelectedItem().toString());
                reference.child("month").setValue(Month.getSelectedItem().toString());
                reference.child("time").setValue(Time.getSelectedItem().toString());

                Toast.makeText (getActivity ().getApplicationContext(),"Request Submitted!",Toast.LENGTH_LONG).show ();
            }
        });
        return myview;
    }
}
