package com.example.fyp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyActivity extends AppCompatActivity {

    TextView order,amount, name, address, phone;
    EditText upi;
    RadioButton cash,online;
    RadioGroup radioGroup;
    Button pay;
    String TAG ="main";
    final int UPI_PAYMENT = 0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_buy);

        toolbar = findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setTitle("");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowHomeEnabled (true);

        order=(TextView)findViewById (R.id.customer_order);
        amount=(TextView)findViewById (R.id.customer_amount);
        name=(TextView)findViewById (R.id.customer_name);
        address=(TextView)findViewById (R.id.customer_add);
        phone=(TextView)findViewById (R.id.customer_phone);
        cash=(RadioButton)findViewById (R.id.pay_cod);
        online=(RadioButton)findViewById (R.id.pay_upi);
        radioGroup=(RadioGroup) findViewById (R.id.radio_group);
        upi=(EditText)findViewById (R.id.upi);
        pay=(Button) findViewById (R.id.pay);

        Intent intent = getIntent ();
        String mAmount = intent.getStringExtra ("price");
        String mOrder = intent.getStringExtra ("title");

        amount.setText (mAmount);
        order.setText (mOrder);

        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=auth.getCurrentUser();
        final String id=firebaseUser.getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users").child(id);

        reference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String user=snapshot.child("name").getValue().toString();
                String add=snapshot.child("address").getValue().toString();
                String phn=snapshot.child("phone").getValue().toString();
                name.setText(user);
                address.setText(add);
                phone.setText(phn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addListenerOnButton();
    }

    private void addListenerOnButton() {
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cash.isChecked ()) {
                    Intent intent =new Intent(getApplicationContext(),ConfirmActivity.class);
                    String Mode="Cash on Delivery";
                    intent.putExtra("mode",Mode);
                    String Name = name.getText().toString();
                    intent.putExtra("name",Name);
                    String Address = address.getText().toString();
                    intent.putExtra("address",Address);
                    String Phone = phone.getText().toString();
                    intent.putExtra("phone",Phone);
                    String Order = order.getText().toString();
                    intent.putExtra("title",Order);
                    String Amount = amount.getText().toString();
                    intent.putExtra("price",Amount);
                    startActivity(intent);

                    //database data upload
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser=auth.getCurrentUser();
                    final String id=firebaseUser.getUid();
                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Users").child(id);
                    reference.child ("order").setValue(order.getText().toString());
                    reference.child ("amount").setValue(amount.getText().toString());

                } else if (online.isChecked ()){
                    if (TextUtils.isEmpty(upi.getText().toString().trim())){
                        Toast.makeText(BuyActivity.this,"Enter UPI Id", Toast.LENGTH_SHORT).show();
                    } else{
                        payUsingUpi(upi.getText().toString(),amount.getText().toString());
                        }
                }
            }
        });
    }

    void payUsingUpi(String upiId, String amount) {
        Log.e("main ", "name " +"--up--"+upiId+"--"+"--"+amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                //.appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                //.appendQueryParameter("tr", "25584584")
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();

        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(BuyActivity.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response "+resultCode );

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);

                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);

                    //data to conformation page
                    Intent intent1 =new Intent(getApplicationContext(),ConfirmActivity.class);
                    String Mode="UPI";
                    intent1.putExtra("mode",Mode);
                    String Name = name.getText().toString();
                    intent1.putExtra("name",Name);
                    String Address = address.getText().toString();
                    intent1.putExtra("address",Address);
                    String Phone = phone.getText().toString();
                    intent1.putExtra("phone",Phone);
                    String Order = order.getText().toString();
                    intent1.putExtra("title",Order);
                    String Amount = amount.getText().toString();
                    intent1.putExtra("price",Amount);
                    startActivity(intent1);

                    //database data upload
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser=auth.getCurrentUser();
                    final String id=firebaseUser.getUid();
                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Users").child(id);
                    reference.child ("order").setValue(order.getText().toString());
                    reference.child ("amount").setValue(amount.getText().toString());
                }
                break;
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(BuyActivity.this)) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user";
                }
            }

            if (status.equals("success")) {
                Toast.makeText(BuyActivity.this, "Transaction successful", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Payment successful: "+approvalRefNo);
            }
            else if("Payment cancelled by user".equals(paymentCancel)) {
                Toast.makeText(BuyActivity.this, "Payment cancelled by user", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Cancelled by user: "+approvalRefNo);

            }
            else {
                Toast.makeText(BuyActivity.this, "Transaction failed! Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Failed payment: "+approvalRefNo);

            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(BuyActivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo ();
            if (netInfo != null && netInfo.isConnected ()
                    && netInfo.isConnectedOrConnecting ()
                    && netInfo.isAvailable ()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}
