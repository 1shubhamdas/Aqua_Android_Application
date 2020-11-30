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

public class MaintenanceAdminActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView maintainrecycler;
    private DatabaseReference mMaintainDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_maintenance_admin);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        mMaintainDatabase = FirebaseDatabase.getInstance ().getReference ().child ("Users").child ("");

        //Recycler
        maintainrecycler = findViewById (R.id.recycler_maintenance);
        LinearLayoutManager layoutManager = new LinearLayoutManager (getApplicationContext (), LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout (true);
        layoutManager.setStackFromEnd (true);
        maintainrecycler.setHasFixedSize (true);
        maintainrecycler.setLayoutManager (layoutManager);
    }


    @Override
    public void onStart() {
        super.onStart ();

        FirebaseRecyclerAdapter<maintain, MaintenanceAdminActivity.HomeViewHolder> adapterOne = new FirebaseRecyclerAdapter<maintain, MaintenanceAdminActivity.HomeViewHolder>
                (
                        maintain.class,
                        R.layout.maintain,
                        MaintenanceAdminActivity.HomeViewHolder.class,
                        mMaintainDatabase
                )
        {
            @Override
            protected void populateViewHolder(MaintenanceAdminActivity.HomeViewHolder homeViewHolder, final maintain model, int position) {
                homeViewHolder.msetName (model.getName ());
                homeViewHolder.msetAddress (model.getAddress ());
                homeViewHolder.msetPhone (model.getPhone ());
                homeViewHolder.msetTime (model.getTime ());
                homeViewHolder.msetDaydate (model.getDaydate ());
                homeViewHolder.msetMonth (model.getMonth ());

                homeViewHolder.myview.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent (getApplicationContext(),CustomerMaintainActivity.class);
                        intent.putExtra ("name",model.getName ());
                        intent.putExtra ("address",model.getAddress ());
                        intent.putExtra ("phone",model.getPhone ());
                        intent.putExtra ("time",model.getTime ());
                        intent.putExtra ("daydate",model.getDaydate ());
                        intent.putExtra ("month",model.getMonth ());
                        startActivity (intent);
                    }
                });
            }
        };
        maintainrecycler.setAdapter (adapterOne);
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
        public void msetTime(String time){
            TextView mTime=myview.findViewById (R.id.time);
            mTime.setText (time);
        }
        public void msetDaydate(String daydate) {
            TextView mDaydate = myview.findViewById (R.id.daydate);
            mDaydate.setText (daydate);
        }
        public void msetMonth(String month) {
            TextView mMonth = myview.findViewById (R.id.month);
            mMonth.setText (month);
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