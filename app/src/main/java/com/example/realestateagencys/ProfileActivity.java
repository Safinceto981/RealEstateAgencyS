package com.example.realestateagencys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileName,profileAge,profileEmail,profilePhone,profileAddress;
    private Button profileEdit;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private  Button changePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName=findViewById(R.id.tvProfileName);
        profileAge=findViewById(R.id.tvProfileAge);
        profileEmail=findViewById(R.id.tvProfileEmail);
        profilePhone=findViewById(R.id.tvProfilePhone);
        profileAddress=findViewById(R.id.tvProfileAddress);
        profileEdit= findViewById(R.id.btnProfileEdit);
        changePass = findViewById(R.id.btnChangePass);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();


        DatabaseReference databaseReference=firebaseDatabase.getReference("users").child(firebaseAuth.getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile= dataSnapshot.getValue(UserProfile.class);
                profileName.setText("Name: " + userProfile.getUserName());
                profileAge.setText("Age: "+userProfile.getUserAge());
                profileEmail.setText("Email: "+userProfile.getUserEmail());
                profilePhone.setText("Phone: "+userProfile.getUserPhone());
                profileAddress.setText("Address: "+userProfile.getUserAddress());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ProfileActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,UpdateProfile.class));

            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, UpdatePass.class));

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return  super.onOptionsItemSelected(item);


    }
}
