package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DeleteProductActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button fish,plants,filter,tank,other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_delete_product);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        fish=findViewById(R.id.delete_fish);
        plants=findViewById(R.id.delete_plants);
        filter=findViewById(R.id.delete_filter);
        tank=findViewById(R.id.delete_tank);
        other=findViewById(R.id.delete_other_items);

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), DeleteActivity.class);
                String btn=fish.getText().toString();
                intent.putExtra("name",btn);
                startActivity(intent);
            }
        });

        plants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),DeleteActivity.class);
                String btn=plants.getText().toString();
                intent.putExtra("name",btn);
                startActivity(intent);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),DeleteActivity.class);
                String btn=filter.getText().toString();
                intent.putExtra("name",btn);
                startActivity(intent);
            }
        });

        tank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),DeleteActivity.class);
                String btn=tank.getText().toString();
                intent.putExtra("name",btn);
                startActivity(intent);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),DeleteActivity.class);
                String btn=other.getText().toString();
                intent.putExtra("name",btn);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}
