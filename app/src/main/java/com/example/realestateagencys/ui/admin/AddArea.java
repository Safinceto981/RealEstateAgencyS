package com.example.realestateagencys.ui.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.realestateagencys.R;

import com.example.realestateagencys.ui.model.Areah;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddArea extends AppCompatActivity implements View.OnClickListener {

    private static final String DUPLICATE_USER_MESSAGE = "User with this email already exist.";
    private static final String USERS_KEY = "users";
    private static final String REQUIRED_FIELDS_MESSAGE = "All fields are required";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String PASSWORD_LENGTH_MESSAGE = "Password must be at least 6 chars long";
    private Button save_address;
    private EditText address;
    private EditText city;
    private EditText state;
    private EditText distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_area);
        save_address = (Button) findViewById(R.id.save_area);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        distance = (EditText) findViewById(R.id.distance);
        save_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Areas");
        String id = databaseReference.push().getKey();
        Areah area = new Areah(id, address.getText().toString(), city.getText().toString(), state.getText().toString(), distance.getText().toString());
        databaseReference.child(id).setValue(area).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("saved", "saved");
                Toast.makeText(AddArea.this,"Added",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
