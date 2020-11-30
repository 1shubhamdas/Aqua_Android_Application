package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentAdminActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView paymentrecycler;
    private DatabaseReference mPaymentDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_payment_admin);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        mPaymentDatabase = FirebaseDatabase.getInstance ().getReference ().child ("Users").child ("");

        //Recycler
        paymentrecycler = findViewById (R.id.recycler_payment);
        LinearLayoutManager layoutManager = new LinearLayoutManager (getApplicationContext (), LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout (true);
        layoutManager.setStackFromEnd (true);
        paymentrecycler.setHasFixedSize (true);
        paymentrecycler.setLayoutManager (layoutManager);
    }


    @Override
    public void onStart() {
        super.onStart ();

        FirebaseRecyclerAdapter<maintain, PaymentAdminActivity.HomeViewHolder> adapterOne = new FirebaseRecyclerAdapter<maintain, PaymentAdminActivity.HomeViewHolder>
                (
                        maintain.class,
                        R.layout.payment,
                        PaymentAdminActivity.HomeViewHolder.class,
                        mPaymentDatabase
                )
        {
            @Override
            protected void populateViewHolder(PaymentAdminActivity.HomeViewHolder homeViewHolder, final maintain model, int position) {
                homeViewHolder.msetName (model.getName ());
                homeViewHolder.msetAddress (model.getAddress ());
                homeViewHolder.msetPhone (model.getPhone ());
                homeViewHolder.msetOrder (model.getOrder ());
                homeViewHolder.msetAmount (model.getAmount ());

                homeViewHolder.myview.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent (getApplicationContext(),ConfirmActivity.class);
                        intent.putExtra ("name",model.getName ());
                        intent.putExtra ("address",model.getAddress ());
                        intent.putExtra ("phone",model.getPhone ());
                        intent.putExtra ("order",model.getOrder ());
                        intent.putExtra ("amount",model.getAmount ());
                        startActivity (intent);
                    }
                });
            }
        };
        paymentrecycler.setAdapter (adapterOne);
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder{

        View myview;
        public HomeViewHolder(@NonNull View itemView) {
            super (itemView);
            myview=itemView;
        }
        public void msetName(String name){
            TextView mName=myview.findViewById (R.id.name);
            mName.setText (name);
        }
        public void msetAddress(String address){
            TextView mAddress=myview.findViewById (R.id.address);
            mAddress.setText (address);
        }
        public void msetPhone(String phone) {
            TextView mPhone = myview.findViewById (R.id.phone);
            mPhone.setText (phone);
        }
        public void msetOrder(String order){
            TextView mOrder=myview.findViewById (R.id.order_item);
            mOrder.setText (order);
        }
        public void msetAmount(String amount) {
            TextView mAmount = myview.findViewById (R.id.amount);
            mAmount.setText (amount);
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