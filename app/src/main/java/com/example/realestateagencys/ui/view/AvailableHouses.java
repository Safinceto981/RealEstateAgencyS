package com.example.realestateagencys.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.adapter.AvailableHouseAdapter;
import com.example.realestateagencys.ui.model.HouseType;
import com.example.realestateagencys.ui.model.Areah;
import com.example.realestateagencys.ui.model.House;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AvailableHouses extends AppCompatActivity {



    private RecyclerView available_houses_recylerview;
    private AvailableHouseAdapter availableHouseAdapter;
    private List<House> allHousesInSelectedArea;
    private Areah selectedArea;
    private HouseType selectedHouseType;

    private String selectedDate;
private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_houses);
        available_houses_recylerview = findViewById(R.id.recycler_view_available_houses);
        allHousesInSelectedArea = new ArrayList<>();
        getUserSelectedData();
        getSupportActionBar().setTitle("Available Houses");
        initWidgets();
    }

    private void getUserSelectedData() {
        selectedArea = (Areah) getIntent().getSerializableExtra("selectedArea");
        selectedHouseType = (HouseType) getIntent().getSerializableExtra("selectedHouseType");
        selectedDate=getIntent().getStringExtra("SelectedDate");
    }

    private void initWidgets() {
        available_houses_recylerview.setHasFixedSize(true);
        available_houses_recylerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        allHousesInSelectedArea = getAllHousesInSelectedArea();
        availableHouseAdapter = new AvailableHouseAdapter(getApplicationContext(), allHousesInSelectedArea, this, selectedArea,selectedDate);
        availableHouseAdapter.notifyDataSetChanged();
        available_houses_recylerview.setAdapter(availableHouseAdapter);
    }

    private List<House> getAllHousesInSelectedArea() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("houses");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    House house = snapshot.getValue(House.class);
                    if (house.getArea().getId().equals(selectedArea.getId())) {
                        if (selectedHouseType.getName().equals(house.getType().getName())) {
                            allHousesInSelectedArea.add(house);
                        } else if (selectedHouseType.getName().equals("All Types")) {
                            allHousesInSelectedArea.add(house);
                        }
                    }
                }
               /* allHousesInSelectedArea.add(House house1,House house2);
                Collections.sort(allHousesInSelectedArea, new Comparator<House>() {
                    @Override
                    public int compare(House house1, House house2) {
                    return Integer.compare(house1.getR(), house2.getRate());

                        return 0;
                    }
                });
           /*Collections.sort(allHousesInSelectedArea, new Comparator<House>() {
               @Override
               public int compare(House house1, House house2) {
                   return String.compare(house1.getName(),house2.getName());
               }
           });*/

                    Arrays.sort(new List[]{allHousesInSelectedArea});

                availableHouseAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return allHousesInSelectedArea;
    }
}