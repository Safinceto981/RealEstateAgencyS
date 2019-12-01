package com.example.realestateagencys.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestateagencys.MainActivity;
import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.adapter.AreaAdapter;
import com.google.android.material.navigation.NavigationView;
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
import com.example.realestateagencys.ui.model.Areah;


public class NearbyAreas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private RecyclerView recyclerView;
    private List<Areah> areas;
    private AreaAdapter areaAdapter;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_area);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setTitle("Choose Nearby Area");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.area_recycler_view);
        initWidgets();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {


            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.my_arrangments:
                intent = new Intent(this, MyArrangements.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initWidgets();
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
                areaAdapter.notifyDataSetChanged();
            }

      @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void initWidgets() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        areas = new ArrayList<>();
        areaAdapter = new AreaAdapter(getApplicationContext(), areas);
        areaAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(areaAdapter);
    }
}
