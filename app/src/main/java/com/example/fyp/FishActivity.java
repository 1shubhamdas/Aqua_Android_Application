package com.example.fyp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class FishActivity extends AppCompatActivity {

    EditText addId, addTitle, addDescription, addPrice;
    ImageView addImage;
    Button addSubmit;
    Toolbar toolbar;
    public StorageReference AquaRef;
    public Uri addUri;
    DatabaseReference mFishDatabase,mPlantsDatabase,mTankDatabase,mFilterDatabase,mOtherDatabase;
    private StorageTask uploadTask;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_fish);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        addId = (EditText) findViewById (R.id.add_id);
        addTitle=(EditText)findViewById (R.id.add_title);
        addDescription=(EditText)findViewById (R.id.add_description);
        addPrice=(EditText)findViewById (R.id.add_price);
        addImage=(ImageView)findViewById (R.id.add_image);
        addSubmit = (Button) findViewById (R.id.add_submit);

            AquaRef= FirebaseStorage.getInstance().getReference();

            addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileChooser();
                }
            });

            addSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id=addId.getText().toString();
                    String title=addTitle.getText().toString();
                    String description=addDescription.getText().toString();
                    String price=addPrice.getText().toString();
                    if(addUri==null){
                        Toast.makeText(FishActivity.this,"Please upload an Image",Toast.LENGTH_SHORT).show();
                    }
                    if(TextUtils.isEmpty(id))
                    {
                        Toast.makeText(FishActivity.this,"Please upload an Id",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(title)){
                        Toast.makeText(FishActivity.this,"Please upload a Title",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(description)){
                        Toast.makeText(FishActivity.this,"Please upload a Description",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        FileUploader(id,title,description,price);
                    }
                }
            });

    }

    private void FileChooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            addUri=data.getData();
            addImage.setImageURI(addUri);
        }
    }
    private String getExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void FileUploader(final String id, final String title,final String description, final  String price ) {

        final StorageReference aquareference= AquaRef.child(System.currentTimeMillis()+"."+getExtension(addUri));

        uploadTask=aquareference.putFile(addUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        aquareference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                HashMap<String,Object> hashMap=new HashMap<>();
                                hashMap.put("title",title);
                                hashMap.put("price",price);
                                hashMap.put("description",description);
                                hashMap.put("image",String.valueOf (uri));
                                mFishDatabase = FirebaseDatabase.getInstance ().getReference ("mFishDatabase").child(id);
                                mFishDatabase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void> () {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Operation Successful!", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(getApplicationContext(), FishActivity.class);
                                        }
                                    }
                                });
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
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
