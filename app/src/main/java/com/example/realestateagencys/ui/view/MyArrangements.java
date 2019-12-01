package com.example.realestateagencys.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.model.HouseArrangment;
import com.example.realestateagencys.ui.adapter.ArrangmentsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyArrangements extends AppCompatActivity {

    private RecyclerView arrangments_recycler_view;
    private List<HouseArrangment> arrangments;
    private ArrangmentsAdapter arrangmentsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_arrangements);
        getSupportActionBar().setTitle("Recent Arrangments");
        arrangments_recycler_view = findViewById(R.id.arrangment_recycler_view);
        initWidgets();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initWidgets();
        final DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("arrangments");
        final String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HouseArrangment arrangment = snapshot.getValue(HouseArrangment.class);
                    if (arrangment.getUser().equals(user)) {
                        arrangments.add(arrangment);
                    }
                }
                Collections.sort(arrangments, new Comparator<HouseArrangment>() {
                    @Override
                    public int compare(HouseArrangment arrangment1, HouseArrangment arrangment2) {
                        try {
                            return df.parse(arrangment1.getArrangmentDate()).compareTo(df.parse(arrangment2.getArrangmentDate()));
                        } catch (Exception e) {

                        }
                        return 0;
                    }
                });
                arrangmentsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void initWidgets() {
        arrangments_recycler_view.setHasFixedSize(true);
       arrangments_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        arrangments = new ArrayList<>();
        arrangmentsAdapter = new ArrangmentsAdapter(getApplicationContext(), arrangments, this);
        arrangmentsAdapter.notifyDataSetChanged();
       arrangments_recycler_view.setAdapter(arrangmentsAdapter);
    }
}
