package com.example.realestateagencys.ui.housearrangment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.model.HouseArrangment;

import com.example.realestateagencys.ui.model.Areah;
import com.example.realestateagencys.ui.model.House;
import com.example.realestateagencys.ui.model.HouseType;
import com.example.realestateagencys.ui.view.AvailableHouses;
import com.example.realestateagencys.ui.view.HouseTypes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.provider.Settings.System.DATE_FORMAT;

public class HouseOptionsFilter extends AppCompatActivity implements View.OnClickListener {

    private Button search_houses;
    private Button date_selector;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private TextView house_type;

    private Button house_type_selector;
    private Calendar calendar;
    private static final int REQUEST_CODE = 1;

    private static final String AM = "AM";
    private static final String PM = "PM";
    private static String TIME_AM_PM = "";
    private static final String TIME_SEPARATOR = ":00";
    private static final String TIME_DIVIDER = "12";
    public static final String DATE_FORMAT = "dd MMM yyyy";
    private Spinner house_area_spinner_filter;
    private Areah selectedArea;
    private HouseType selectedHouseType;
    private List<Areah> areas;
    private ArrayList<House> availableHouses;
    private List<HouseArrangment> houseArrangments;
    private List<House> allHouse;

    String date = getCurrentDate(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_options_filter);
        search_houses = (Button) findViewById(R.id.search_house);
        date_selector = (Button) findViewById(R.id.date_selector);

       house_type_selector = (Button) findViewById(R.id.house_type_selector);
        house_type = (TextView) findViewById(R.id.house_type_selector);
        house_area_spinner_filter = (Spinner) findViewById(R.id.house_area_spinner_filter);
        areas = new ArrayList<>();
        calendar = Calendar.getInstance();
        search_houses.setOnClickListener(this);
        date_selector.setOnClickListener(this);

        date_selector.setText(date);



        house_type_selector.setOnClickListener(this);
        selectedArea = getSelectedArea();
        selectedHouseType = new HouseType("All Types", "");
        getSupportActionBar().setTitle("Search Houses");

        fetchAreas();
        initFilterDetails();

    }
    private void initFilterDetails() {
        String startTime = getCurrentTime(1);
        if (startTime.equals("12:00 AM")) {
            String date = getCurrentDate(1);
            date_selector.setText(date);
        } else {
            String date = getCurrentDate(0);
            date_selector.setText(date);
        }
        house_type_selector.setText(selectedHouseType.getName());

    }


   private String getCurrentTime(int step) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, step);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        return formatTime(hour);
    }



    private String getCurrentDate(int step) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, step);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return formatDate(year, month, day);
    }

    private Areah getSelectedArea() {
        Areah selectedArea = (Areah) getIntent().getSerializableExtra("selectedHouseDetails");
        return selectedArea;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_selector:
                selectDate();
                break;

            case R.id.house_type_selector:
                selectHouseType();
                break;
            case R.id.search_house:
                searchAvailableHouses();
                break;
        }
    }



    private void searchAvailableHouses() {


            if (isValidDate()) {
                Intent intent = new Intent(this, AvailableHouses.class);
                intent.putExtra("selectedArea", selectedArea);
                intent.putExtra("selectedDate", date_selector.getText().toString());

                intent.putExtra("selectedHouseType", selectedHouseType);

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Date must be now or later", Toast.LENGTH_LONG).show();
            }
        }

    private boolean isValidDate() {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            if (df.parse(date_selector.getText().toString()).before(df.parse(getCurrentDate(0)))) {
                return false;
            }
        } catch (Exception e) {

        }
        return true;
    }

    private void selectHouseType() {
        Intent intent = new Intent(this, HouseTypes.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void selectDate() {
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date_selector.setText(formatDate(year, month, dayOfMonth));
            }
        }, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private static String formatDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }
    private String formatTime(int hour) {
        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hour);
        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
            TIME_AM_PM = AM;
        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
            TIME_AM_PM = PM;
        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? TIME_DIVIDER : datetime.get(Calendar.HOUR) + "";
        return strHrsToShow + TIME_SEPARATOR + " " + TIME_AM_PM;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            selectedHouseType = (HouseType) data.getSerializableExtra("housetype");
            house_type.setText(selectedHouseType.getName());
        }
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
                ArrayAdapter<Areah> areaArrayAdapter = new ArrayAdapter<Areah>(getApplicationContext(), R.layout.spinner, areas);
                areaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                house_area_spinner_filter.setAdapter(areaArrayAdapter);
                setSelectedArea();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setSelectedArea() {
        for (int index = 0; index < areas.size(); index++) {
            if (selectedArea.getId().equals(areas.get(index).getId())) {
                house_area_spinner_filter.setSelection(index);
            }
        }
    }

}
