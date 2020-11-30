package com.example.fyp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteActivity extends AppCompatActivity {

    EditText deleteId;
    Button deleteSubmit;
    Toolbar toolbar;
    DatabaseReference mFishDatabase,mPlantsDatabase,mTankDatabase,mFilterDatabase,mOtherDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_delete);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        mFishDatabase = FirebaseDatabase.getInstance ().getReference ("mFishDatabase");
        mPlantsDatabase = FirebaseDatabase.getInstance ().getReference ("mPlantsDatabase");
        mTankDatabase = FirebaseDatabase.getInstance ().getReference ("mTankDatabase");
        mFilterDatabase = FirebaseDatabase.getInstance ().getReference ("mFilterDatabase");
        mOtherDatabase = FirebaseDatabase.getInstance ().getReference ("mOtherDatabase");

        deleteId = (EditText) findViewById (R.id.delete_id);
        deleteSubmit = (Button) findViewById (R.id.delete_submit);

        String name=getIntent().getStringExtra("name");

        if(name.equals("Fish")){

            deleteSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String id=deleteId.getText().toString();
                    if(TextUtils.isEmpty(id)){
                        Toast.makeText(getApplicationContext(),"Enter Id",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("mFishDatabase").child(id);
                        reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void> () {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Product deleted!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        if(name.equals("Plants")){

            deleteSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String id=deleteId.getText().toString();
                    if(TextUtils.isEmpty(id)){
                        Toast.makeText(getApplicationContext(),"Enter Id",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("mPlantsDatabase").child(id);
                        reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void> () {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Product deleted!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }

        if(name.equals("Filter")){

            deleteSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String id=deleteId.getText().toString();
                    if(TextUtils.isEmpty(id)){
                        Toast.makeText(getApplicationContext(),"Enter Id",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("mFilterDatabase").child(id);
                        reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void> () {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Product deleted!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }

        if(name.equals("Tank")){

            deleteSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String id=deleteId.getText().toString();
                    if(TextUtils.isEmpty(id)){
                        Toast.makeText(getApplicationContext(),"Enter Id",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("mTankDatabase").child(id);
                        reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void> () {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Product deleted!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }

        if(name.equals("Other items")){

            deleteSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String id=deleteId.getText().toString();
                    if(TextUtils.isEmpty(id)){
                        Toast.makeText(getApplicationContext(),"Enter Id",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("mOtherDatabase").child(id);
                        reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void> () {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Product deleted!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}