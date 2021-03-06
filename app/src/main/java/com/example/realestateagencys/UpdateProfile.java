package com.example.realestateagencys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    private EditText newUserName,newUserEmail,newUserAge,newUserPhone,newUserAddress;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        newUserName = findViewById(R.id.etUpdateName);
        newUserEmail = findViewById(R.id.etUpdateEmail);
        newUserAge = findViewById(R.id.etUpdateAge);
        newUserPhone = findViewById(R.id.etUpdatePhone);
        newUserAddress = findViewById(R.id.etUpdateAddress);

        save = findViewById(R.id.btnUpdateSave);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference=firebaseDatabase.getReference("users").child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile= dataSnapshot.getValue(UserProfile.class);
                newUserName.setText(userProfile.getUserName());
                newUserAge.setText(userProfile.getUserAge());
                newUserEmail.setText(userProfile.getUserEmail());
                newUserPhone.setText(userProfile.getUserPhone());
                newUserAddress.setText(userProfile.getUserAddress());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateProfile.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = newUserName.getText().toString();
                String age = newUserAge.getText().toString();
                String email = newUserEmail.getText().toString();
                String phone = newUserPhone.getText().toString();
                String address = newUserAddress.getText().toString();

                UserProfile userProfile= new UserProfile(age,email,name,phone,address);
                databaseReference.child(firebaseAuth.getUid()).setValue(userProfile);

                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return  super.onOptionsItemSelected(item);


    }

}
