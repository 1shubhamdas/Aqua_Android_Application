package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CustomerFeedbackActivity extends AppCompatActivity {

    private TextView name,feedback,rating,phone;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_customer_feedback);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        name = findViewById (R.id.customer_name);
        feedback = findViewById (R.id.feedback);
        rating = findViewById (R.id.customer_rating);
        phone=findViewById (R.id.customer_phone);

        Intent intent = getIntent ();
        String mName = intent.getStringExtra ("name");
        String mFeedback = intent.getStringExtra ("feedback");
        String mRating = intent.getStringExtra ("rating");
        String mPhone=intent.getStringExtra ("phone");

        name.setText (mName);
        feedback.setText (mFeedback);
        rating.setText (mRating);
        phone.setText (mPhone);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}