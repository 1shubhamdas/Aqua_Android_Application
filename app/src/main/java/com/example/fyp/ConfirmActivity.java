package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConfirmActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    Handler handler;
    Toolbar toolbar;
    TextView name, address, phone, order, amount, mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView (R.layout.activity_confirm);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

    order=(TextView)findViewById (R.id.customer_order);
    amount=(TextView)findViewById (R.id.customer_amount);
    name=(TextView)findViewById (R.id.customer_name);
    address=(TextView)findViewById (R.id.customer_add);
    phone=(TextView)findViewById (R.id.customer_phone);
    mode=(TextView)findViewById (R.id.customer_mode);

    Intent intent = getIntent ();
    String Name = intent.getStringExtra ("name");
    String Address = intent.getStringExtra ("address");
    String Phone = intent.getStringExtra ("phone");
    String Amount = intent.getStringExtra ("price");
    String Order = intent.getStringExtra ("title");
    String Mode = intent.getStringExtra ("mode");

        name.setText (Name);
        address.setText (Address);
        phone.setText (Phone);
        order.setText (Order);
        amount.setText (Amount);
        mode.setText (Mode);

    firebaseAuth= FirebaseAuth.getInstance();
    handler=new Handler ();
        handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            FirebaseUser currentUser=firebaseAuth.getCurrentUser();
            Intent intent=new Intent(ConfirmActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    },5000);
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}