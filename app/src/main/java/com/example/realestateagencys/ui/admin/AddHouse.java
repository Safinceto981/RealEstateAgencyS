package com.example.realestateagencys.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.realestateagencys.MainActivity;
import com.example.realestateagencys.ProfileActivity;
import com.example.realestateagencys.R;
import com.example.realestateagencys.SecondActivity;
import com.example.realestateagencys.ui.model.HouseType;
import com.example.realestateagencys.ui.model.Areah;
import com.example.realestateagencys.ui.model.House;
import com.example.realestateagencys.ui.view.MyArrangements;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AddHouse extends AppCompatActivity implements View.OnClickListener {

    private Button save_house;
    private EditText house_name;
    private EditText house_number;
    private Spinner house_areas;
    private Spinner house_types;
    private List<Areah> areas;
    private List<HouseType> houseTypes;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);
        save_house = (Button) findViewById(R.id.save_house);
        house_name = (EditText) findViewById(R.id.house_name);
        house_areas = (Spinner) findViewById(R.id.house_area_spinner);
        house_types = (Spinner) findViewById(R.id.house_type_spinner);
        areas = new ArrayList<>();
       houseTypes = new ArrayList<>();
        fetchAreas();
        fetchHouseType();
        save_house.setOnClickListener(this);
        setTitle("Add house");
    }

    private void fetchAreas() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Areas");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Areah area = snapshot.getValue(Areah.class);
                    areas.add(area);
                }
                Collections.sort(areas, new Comparator<Areah>() {
                    @Override
                    public int compare(Areah area1, Areah area2) {
                        return Double.compare(Double.parseDouble(area1.getDistance()), (Double.parseDouble(area2.getDistance())));
                    }
                });
                ArrayAdapter<Areah> areaArrayAdapter = new ArrayAdapter<Areah>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, areas);
              areaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               house_areas.setAdapter(areaArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void fetchHouseType() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("housetype");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HouseType houseType = snapshot.getValue(HouseType.class);
                    houseTypes.add(houseType);
                }
                ArrayAdapter<HouseType> houseTypeArrayAdapter = new ArrayAdapter<HouseType>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, houseTypes);
               houseTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                house_types.setAdapter(houseTypeArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("houses");
        String id = databaseReference.push().getKey();
        Areah area = (Areah) house_areas.getSelectedItem();
        HouseType houseType = (HouseType) house_types.getSelectedItem();
        House house = new House(id,house_name.getText().toString(),houseType,area);

        databaseReference.child(id).setValue(house).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("saved", "saved");
                Toast.makeText(AddHouse.this,"Added",Toast.LENGTH_SHORT).show();
            }
        });
    }
 @Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
}

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(AddHouse.this, MainActivity.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutMenu:
                Logout();
                break;

            case R.id.profileMenu:
                startActivity(new Intent(AddHouse.this, ProfileActivity.class));
                break;


            case R.id.refreshMenu:
                finish();
                startActivity(new Intent(AddHouse.this, SecondActivity.class));
                break;


            case R.id.myArrangementsBtn:
                startActivity(new Intent(AddHouse.this, MyArrangements.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}

