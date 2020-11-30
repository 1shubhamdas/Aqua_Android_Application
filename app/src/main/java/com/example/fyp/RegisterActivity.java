package com.example.fyp;

import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username,email,address,password,phone;
    Button register;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);

            username=findViewById(R.id.username);
            email=findViewById(R.id.email);
            password=findViewById(R.id.password);
            register=findViewById(R.id.register);
            phone=findViewById(R.id.phone);
            address=findViewById (R.id.address);
            auth=FirebaseAuth.getInstance();

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Username=username.getText().toString();
                    String Email=email.getText().toString();
                    String Password=password.getText().toString();
                    String Address=address.getText().toString();
                    String Phone=phone.getText().toString();
                    if(TextUtils.isEmpty(Username) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)|| TextUtils.isEmpty(Address)||TextUtils.isEmpty(Phone)){
                        Toast.makeText(RegisterActivity.this,"All fields must be filled",Toast.LENGTH_SHORT).show();
                    }
                    else if (Password.length()< 5){
                        Toast.makeText(RegisterActivity.this,"Password must above 5 characters",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        register(Username,Email,Password,Address,Phone);
                    }
                }
            });
        }
        private void register(final String username, final String email, final String password,final String address,final String phone){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult> () {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser firebaseUser=auth.getCurrentUser();
                        assert firebaseUser !=null;
                        String userid=firebaseUser.getUid();
                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                        HashMap<String, Object> hashMap=new HashMap<>();
                        hashMap.put("id",userid);
                        hashMap.put("name",username);
                        hashMap.put("email",email);
                        hashMap.put ("address",address);
                        hashMap.put("phone",phone);
                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,"Registration successful!",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Enter Valid Email Id",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

}