package com.example.realestateagencys.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.model.HouseArrangment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.provider.Settings.System.DATE_FORMAT;

public class MyArrangmentsDetails extends AppCompatActivity implements View.OnClickListener {

    private ImageView house_type_image;
    private TextView house_name;
    private TextView address_location;
    private Button finish_arrangment;

    private TextView date;


    //    private Button extend_booking;
    private HouseArrangment houseArrangment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_arrangment_details);
        house_name = (TextView) findViewById(R.id.house_name_arrangment_details);
        address_location = (TextView) findViewById(R.id.address_location_on_arrangment_details);

        date = (TextView) findViewById(R.id.date_on_arrangment_details);

        finish_arrangment = (Button) findViewById(R.id.finish_trip);
//        extend_booking = (Button) findViewById(R.id.extend_trip);
        finish_arrangment.setOnClickListener(this);
//        extend_booking.setOnClickListener(this);
        getSupportActionBar().setTitle("Arrangment Details");
        houseArrangment = (HouseArrangment) getIntent().getSerializableExtra("arrangments");
        if (houseArrangment.getArrangmentDate().trim().equals(getCurrentDate(0).trim())) {
            finish_arrangment.setText("CANCEL");

        } else if (isCancel()) {
            finish_arrangment.setText("CANCEL");
        }

        if (houseArrangment.isComplete()) {
            finish_arrangment.setVisibility(View.GONE);
//            extend_booking.setVisibility(View.GONE);
        }
        fetchDetailsForMyArrangments();

    }



    private void fetchDetailsForMyArrangments() {
        house_name.setText(houseArrangment.getHouse().getName());
        address_location.setText(houseArrangment.getArea().getAddress());

        date.setText(houseArrangment.getArrangmentDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish_trip:
                finishTrip();
                break;
            /*case R.id.extend_trip:
                Intent intent = new Intent(this, ExtendTrip.class);
                intent.putExtra("booking", carBooking);
                startActivity(intent);
                break;*/
        }
    }

    private void finishTrip() {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("arrangments");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HouseArrangment arrangment = snapshot.getValue(HouseArrangment.class);
                    if (houseArrangment.getId().equals(arrangment.getId())) {
                        houseArrangment.setComplete(true);

                        databaseReference.child(arrangment.getId()).setValue(houseArrangment).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                Intent intent = new Intent(getApplicationContext(), NearbyAreas.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }










    private String getCurrentDate(int step) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, step);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return formatDate(year, month, day);
    }

    private static String formatDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    private boolean isCancel() {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            if ((df.parse(houseArrangment.getArrangmentDate()).before(df.parse(getCurrentDate(0)))) ){
                return false;
            }
        } catch (Exception e) {

        }
        return true;
    }
}

