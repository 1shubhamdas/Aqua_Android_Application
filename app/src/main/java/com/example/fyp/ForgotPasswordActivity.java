package com.example.fyp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText registeredEmail;
    Button forgotPassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_forgot_password);

        registeredEmail=findViewById(R.id.email);
        forgotPassword=findViewById(R.id.reset);
        firebaseAuth =FirebaseAuth.getInstance();

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword.setEnabled(false);
                String txtEmail=registeredEmail.getText().toString();

                if(TextUtils.isEmpty(registeredEmail.getText())){
                    Toast.makeText(ForgotPasswordActivity.this,"Enter your registered email address",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(txtEmail).addOnCompleteListener(new OnCompleteListener<Void> () {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this,"Email sent successfully!",Toast.LENGTH_LONG).show();
                            }
                            else{
                                String error =task.getException().getMessage();
                                Toast.makeText(ForgotPasswordActivity.this,error,Toast.LENGTH_SHORT).show();
                            }
                            forgotPassword.setEnabled(true);
                        }
                    });
                }
            }
        });
    }

}