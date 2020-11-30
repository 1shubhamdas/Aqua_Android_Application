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

public class FeedbackAdminActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView feedbackrecycler;
    private DatabaseReference mFeedbackDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_feedback_admin);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        mFeedbackDatabase = FirebaseDatabase.getInstance ().getReference ().child ("Users").child ("");

        //Recycler
        feedbackrecycler = findViewById (R.id.recycler_feedback);
        LinearLayoutManager layoutManager = new LinearLayoutManager (getApplicationContext (), LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout (true);
        layoutManager.setStackFromEnd (true);
        feedbackrecycler.setHasFixedSize (true);
        feedbackrecycler.setLayoutManager (layoutManager);

    }

    @Override
    public void onStart() {
        super.onStart ();

        FirebaseRecyclerAdapter<Data, HomeViewHolder> adapterOne = new FirebaseRecyclerAdapter<Data, FeedbackAdminActivity.HomeViewHolder>
                (
                        Data.class,
                        R.layout.feedback,
                        FeedbackAdminActivity.HomeViewHolder.class,
                        mFeedbackDatabase
                )
        {
            @Override
            protected void populateViewHolder(FeedbackAdminActivity.HomeViewHolder homeViewHolder, final Data model, int position) {
                homeViewHolder.msetName (model.getName ());
                homeViewHolder.msetFeedback (model.getFeedback ());
                homeViewHolder.msetRating (model.getRating ());
                homeViewHolder.msetPhone (model.getPhone ());

                homeViewHolder.myview.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent (getApplicationContext(),CustomerFeedbackActivity.class);
                        intent.putExtra ("name",model.getName ());
                        intent.putExtra ("feedback",model.getFeedback ());
                        intent.putExtra ("rating",model.getRating ());
                        intent.putExtra ("phone",model.getPhone ());
                        startActivity (intent);
                    }
                });
            }
        };
        feedbackrecycler.setAdapter (adapterOne);
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
        public void msetFeedback(String feedback){
            TextView mFeedback=myview.findViewById (R.id.feedback);
            mFeedback.setText (feedback);
        }
        public void msetRating(String rating){
            TextView mRating=myview.findViewById (R.id.rating);
            mRating.setText (rating);
        }
        public void msetPhone(String phone) {
            TextView mPhone = myview.findViewById (R.id.phone);
            mPhone.setText (phone);
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