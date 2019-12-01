package com.example.realestateagencys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.realestateagencys.ui.admin.AddArea;
import com.example.realestateagencys.ui.admin.AddHouse;
import com.example.realestateagencys.ui.admin.AddHouseType;
import com.example.realestateagencys.ui.view.AvailableHouses;
import com.example.realestateagencys.ui.view.MyArrangements;
import com.example.realestateagencys.ui.view.NearbyAreas;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class SecondActivity extends AppCompatActivity {
    private Button logout,searchAreaBtn,addType,addHouse,avHouses;
    private FirebaseAuth firebaseAuth;
    private Button btnSpinnerSearch;
    private Spinner sp;
    private Spinner sp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        firebaseAuth = FirebaseAuth.getInstance();

        logout = (Button) findViewById(R.id.btnLogout);

        avHouses=(Button)findViewById(R.id.btnAvHouses);

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Logout();
            }
        });


        addType=(Button) findViewById(R.id.addType);
        addHouse=(Button)findViewById(R.id.addHouse);
      /*  Spinner spinnerChoice = (Spinner)findViewById(R.id.spinnerChoiceType);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(SecondActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.choiceType));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoice.setAdapter(myAdapter);



        Spinner spinnerChoice2 = (Spinner)findViewById(R.id.spinnerChoicePlace);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(SecondActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.choicePlace));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoice2.setAdapter(myAdapter2);


        Spinner spinnerChoice3 = (Spinner)findViewById(R.id.spinnerChoicePlaceType);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(SecondActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.choicePlaceType));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoice3.setAdapter(myAdapter3);



        Button buttonSearch = (Button) findViewById(R.id.btnSpinnerSearch);*/
       /*  buttonSearch.setOnClickListener(new View.OnClickListener() {

           public void onClick(View v) {
                if
                // TODO Auto-generated method stub

            }
        });*/



        searchAreaBtn=findViewById(R.id.searchAreaBtn);

       sp= findViewById(R.id.spinnerChoiceType);
       List<String> categories = new ArrayList<>();
       categories.add(0,"Изберете опция");
       categories.add(1,"Под наем");
        categories.add(2,"Продава");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(dataAdapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose category")
                )
                {
                  //  Toast.makeText(parent.getContext(),
                   //         "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                    //        Toast.LENGTH_SHORT).show();

                }else
                {
                   // String item = parent.getItemAtPosition(position).toString();
                  //  Toast.makeText(parent.getContext(), "Selected"+item,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchAreaBtn.setOnClickListener(new View.OnClickListener() {

           @Override public void onClick(View v) {
              startActivity(new Intent(SecondActivity.this, NearbyAreas.class));
           }
            });
        addType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, AddHouseType.class));
            }
        });

        addHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, AddHouse.class));
            }
        });


        avHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, AvailableHouses.class));
            }
        });

        sp1= findViewById(R.id.spinnerChoicePlace);
        List<String> categories1 = new ArrayList<>();
        categories1.add(0,"Изберете град");


        categories1.add(1,"София");
        categories1.add(2,"Пловдив");
        categories1.add(3,"Варна");
        categories1.add(4,"Бургас");
        categories1.add(5,"Велико Търново");
        categories1.add(6,"Стара Загора");

        ArrayAdapter<String> dataAdapter1;
        dataAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories1);

        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp1.setAdapter(dataAdapter1);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose град")
                )
                {


                }else
                {
                    //String item = parent.getItemAtPosition(position).toString();
                   // Toast.makeText(parent.getContext(), "Selected"+item,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp = (Spinner)findViewById(R.id.spinnerChoiceType);
        sp1 = (Spinner)findViewById(R.id.spinnerChoicePlace);
        btnSpinnerSearch=(Button)findViewById(R.id.btnSpinnerSearch);
        btnSpinnerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Toast.makeText(SecondActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(sp.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(sp1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();*/
                //Sofia
                if ((
                        valueOf((sp.getSelectedItem())) == "Под наем") && valueOf((sp1.getSelectedItem())) == "София") {

                    startActivity(new Intent(SecondActivity.this, RentActivity.Sofia.class));


                }

                //Plovdiv
                if ((
                        valueOf((sp.getSelectedItem())) == "Под наем") && valueOf((sp1.getSelectedItem())) == "Пловдив") {

                    startActivity(new Intent(SecondActivity.this, RentActivity.Plovdiv.class));
                }
                //Varna
                if ((
                        valueOf((sp.getSelectedItem())) == "Под наем") && valueOf((sp1.getSelectedItem())) == "Варна") {

                    startActivity(new Intent(SecondActivity.this, RentActivity.Varna.class));


                }

                //Burgas
                if ((
                        valueOf((sp.getSelectedItem())) == "Под наем") && valueOf((sp1.getSelectedItem())) == "Бургас") {

                    startActivity(new Intent(SecondActivity.this, RentActivity.Burgas.class));

                }
                //Veliko Tyrnovo
                if ((
                        valueOf((sp.getSelectedItem())) == "Под наем") && valueOf((sp1.getSelectedItem())) == "Велико Търново") {

                    startActivity(new Intent(SecondActivity.this, RentActivity.VelikoTyrnovo.class));

                }
                //Stara Zagora
                if ((
                        valueOf((sp.getSelectedItem())) == "Под наем") && valueOf((sp1.getSelectedItem())) == "Стара Загора") {

                    startActivity(new Intent(SecondActivity.this, RentActivity.StaraZagora.class));


                }
                //Sofia
                if ((
                        valueOf((sp.getSelectedItem())) == "Продава") && valueOf((sp1.getSelectedItem())) == "София") {
                    startActivity(new Intent(SecondActivity.this, SellActivity.Sofia.class));

                }
                //Plovdiv
                if ((
                        valueOf((sp.getSelectedItem())) == "Продава") && valueOf((sp1.getSelectedItem())) == "Пловдив") {
                    startActivity(new Intent(SecondActivity.this, SellActivity.Plovdiv.class));

                }
                //Burgas
                if ((
                        valueOf((sp.getSelectedItem())) == "Продава") && valueOf((sp1.getSelectedItem())) == "Бургас") {
                    startActivity(new Intent(SecondActivity.this, SellActivity.Burgas.class));

                }
                //Varna
                if ((
                        valueOf((sp.getSelectedItem())) == "Продава") && valueOf((sp1.getSelectedItem())) == "Варна") {
                    startActivity(new Intent(SecondActivity.this, SellActivity.Varna.class));

                }

                //Veliko Tyrnovo
                if ((
                        valueOf((sp.getSelectedItem())) == "Продава") && valueOf((sp1.getSelectedItem())) == "Велико Търново") {
                    startActivity(new Intent(SecondActivity.this, SellActivity.VelikoTyrnovo.class));

                }
                //Stara Zagora
                if ((
                        valueOf((sp.getSelectedItem())) == "Продава") && valueOf((sp1.getSelectedItem())) == "Стара Загора") {
                    startActivity(new Intent(SecondActivity.this, SellActivity.StaraZagora.class));

                } else {
                    Toast.makeText(SecondActivity.this, "Моля изберете вид и място", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }




    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutMenu:
                Logout();
                break;

            case R.id.profileMenu:
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));
                break;


            case R.id.refreshMenu:
                finish();
                startActivity(new Intent(SecondActivity.this, SecondActivity.class));
                break;


            case R.id.myArrangementsBtn:
                startActivity(new Intent(SecondActivity.this, MyArrangements.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            parent.getItemAtPosition(pos);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

}


