package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CustomerMaintainActivity extends AppCompatActivity {

    private TextView name,address,phone, time, daydate, month;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_customer_maintain);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        name = findViewById (R.id.customer_name);
        address = findViewById (R.id.customer_address);
        phone = findViewById (R.id.customer_phone);
        time= findViewById (R.id.customer_time);
        daydate = findViewById (R.id.customer_daydate);
        month= findViewById (R.id.customer_month);

        Intent intent = getIntent ();
        String mName = intent.getStringExtra ("name");
        String mAddress = intent.getStringExtra ("address");
        String mPhone = intent.getStringExtra ("phone");
        String mTime = intent.getStringExtra ("time");
        String mDaydate = intent.getStringExtra ("daydate");
        String mMonth = intent.getStringExtra ("month");

        name.setText (mName);
        address.setText (mAddress);
        phone.setText (mPhone);
        time.setText (mTime);
        daydate.setText (mDaydate);
        month.setText (mMonth);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}