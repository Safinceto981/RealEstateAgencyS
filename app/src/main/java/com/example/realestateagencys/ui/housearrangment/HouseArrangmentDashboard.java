package com.example.realestateagencys.ui.housearrangment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.model.Areah;
import com.example.realestateagencys.ui.model.House;
import com.example.realestateagencys.ui.model.HouseArrangment;
import com.example.realestateagencys.ui.model.HouseType;
import com.example.realestateagencys.ui.view.NearbyAreas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HouseArrangmentDashboard extends AppCompatActivity implements View.OnClickListener {

    private Button confirm_arrangments;

    private TextView house_name;
    private TextView address;
    private TextView date;
    private TextView type;

    private Areah selectedArea;
    private House selectedHouse;
    private HouseType selectedHouseType;
  //  private House selectedType;
   // private HouseType selectedHouseType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_arrangments);
        confirm_arrangments = (Button) findViewById(R.id.btn_arrangment_confirmation);

        house_name = (TextView) findViewById(R.id.house_name_for_arrangment_confirmation);
        date = (TextView) findViewById(R.id.date_on_arrangment_confirmation);

        //type = (TextView)findViewById(R.id.type_on_arrangment_confirmation) ;

        address = (TextView) findViewById(R.id.address_location_on_arrangment_confirmation);

        confirm_arrangments.setOnClickListener(this);
        getSupportActionBar().setTitle("Confirm Reservation");
        fetchSelectedHouseDetails();

    }


    private void fetchSelectedHouseDetails() {
        selectedHouse = (House) getIntent().getSerializableExtra("selectedHouse");
        selectedArea = (Areah) getIntent().getSerializableExtra("selectedArea");
        //selectedHouseType = (HouseType) getIntent().getSerializableExtra("selectedHouseType");
        house_name.setText(selectedHouse.getName() + " (" + selectedHouse.getType() + ")" );
      //  selectedHouseType= (House)getIntent().getSerializableExtra("selectedHouseType");

//        house_name.setText(selectedHouse.getName() );
        address.setText(selectedArea.getAddress());
        date.setText(getIntent().getExtras().getString("selectedDate"));

    }

    @Override
    public void onClick(View v) {

        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //String type= getIntent().getExtras().getString("selectedHouse");

       String selectdate = getIntent().getExtras().getString("selectedDate");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Arrangments");
        String id = databaseReference.push().getKey();
        HouseArrangment houseArrangment = new HouseArrangment(id, user, selectedHouse,selectdate,false);
        databaseReference.child(id).setValue(houseArrangment).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Arrangment Confirmed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), NearbyAreas.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });
    }


}
