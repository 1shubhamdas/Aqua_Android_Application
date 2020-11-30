package com.example.fyp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FeedbackFragment extends Fragment {

    private RatingBar mRatingBar;
    EditText query;
    Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView= inflater.inflate (R.layout.fragment_feedback, container, false);

        query=myView.findViewById(R.id.query);
        submit=myView.findViewById(R.id.submit);
        mRatingBar = (RatingBar)myView.findViewById(R.id.ratingBar);

        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=auth.getCurrentUser();
        final String id=firebaseUser.getUid();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = mRatingBar.getRating();
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Users").child(id);
                reference.child("rating").setValue(String.valueOf(rating));
                reference.child ("feedback").setValue(query.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void> () {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity ().getApplicationContext(),"Thank you. We will get back to you soon!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        return myView;
    }
}