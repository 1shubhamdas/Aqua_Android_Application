package com.example.fyp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {

    Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    TextView name, address, phone, order, amount, mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_account);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        order = (TextView) findViewById (R.id.customer_order);
        amount = (TextView) findViewById (R.id.customer_amount);
        name = (TextView) findViewById (R.id.customer_name);
        address = (TextView) findViewById (R.id.customer_add);
        phone = (TextView) findViewById (R.id.customer_phone);
        mode = (TextView) findViewById (R.id.customer_mode);

        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=auth.getCurrentUser();
        final String id=firebaseUser.getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users").child(id);

        reference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String user=snapshot.child("name").getValue().toString();
                String add=snapshot.child("address").getValue().toString();
                String phn=snapshot.child("phone").getValue().toString();
                String amnt=snapshot.child("amount").getValue().toString();
                String ord=snapshot.child("order").getValue().toString();
                name.setText(user);
                address.setText(add);
                phone.setText(phn);
                amount.setText(amnt);
                order.setText(ord);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseAuth = FirebaseAuth.getInstance ();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}