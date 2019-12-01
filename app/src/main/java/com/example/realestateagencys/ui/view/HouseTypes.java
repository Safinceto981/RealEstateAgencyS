package com.example.realestateagencys.ui.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.adapter.HouseTypeAdapter;

import com.example.realestateagencys.ui.model.HouseType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HouseTypes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<HouseType> houseTypes;
    private HouseTypeAdapter houseTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_types);
        recyclerView = findViewById(R.id.house_type_recycler_view);
        initWidgets();
        getSupportActionBar().setTitle("House Type");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("housetype");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HouseType houseType = snapshot.getValue(HouseType.class);
                    houseTypes.add(houseType);
                }

                houseTypeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initWidgets() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        houseTypes = new ArrayList<>();
        houseTypeAdapter = new HouseTypeAdapter(getApplicationContext(), houseTypes, this);
        houseTypeAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(houseTypeAdapter);
    }


}
