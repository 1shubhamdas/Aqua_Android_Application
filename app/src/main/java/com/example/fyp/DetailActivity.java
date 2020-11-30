package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView title;
    private TextView description;
    private TextView price;
    private Button buyNow;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        imageView=findViewById (R.id.image_details);
        title=findViewById (R.id.title_details);
        description=findViewById (R.id.description_details);
        price=findViewById (R.id.price_details);
        buyNow=findViewById (R.id.buy_details);

        Intent intent=getIntent ();
        String mTitle=intent.getStringExtra ("title");
        String mDescription=intent.getStringExtra ("description");
        final String mImage=intent.getStringExtra ("image");
        String mPrice=intent.getStringExtra ("price");

        title.setText (mTitle);
        description.setText (mDescription);
        price.setText (mPrice);

        Picasso.get ().load (mImage).networkPolicy (NetworkPolicy.OFFLINE).into (imageView, new Callback () {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {

                Picasso.get ().load (mImage).into (imageView);
            }
        });

        buyNow.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),BuyActivity.class);
                String order = title.getText().toString();
                intent.putExtra("title",order);
                String amount = price.getText().toString();
                intent.putExtra("price",amount);
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