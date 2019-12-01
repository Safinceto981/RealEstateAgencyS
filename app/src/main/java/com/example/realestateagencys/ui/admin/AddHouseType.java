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
import com.example.realestateagencys.ui.model.HouseType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHouseType extends AppCompatActivity implements View.OnClickListener {

    private Button save_type;
    private EditText type_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house_type);
        save_type = (Button) findViewById(R.id.save_type);
        type_name = (EditText) findViewById(R.id.type_name);
        save_type.setOnClickListener(this);
        setTitle("Add type of house");
    }

    @Override
    public void onClick(View v) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("housetype");
        String id = databaseReference.push().getKey();
        HouseType houseType = new HouseType(type_name.getText().toString(), type_name.getText().toString());
        databaseReference.child(id).setValue(houseType).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("saved", "saved");
                Toast.makeText(getApplicationContext(), "Saved Succesfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

}