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

import com.google.firebase.auth.FirebaseAuth;
public class SecondActivity extends AppCompatActivity {
    private Button logout;
    private FirebaseAuth firebaseAuth;
    private Button btnSpinnerSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        firebaseAuth = FirebaseAuth.getInstance();

        logout = (Button) findViewById(R.id.btnLogout);


        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Logout();
            }
        });


        Spinner spinnerChoice = (Spinner)findViewById(R.id.spinnerChoiceType);
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

        Button buttonSearch = (Button) findViewById(R.id.btnSpinnerSearch);
       /*  buttonSearch.setOnClickListener(new View.OnClickListener() {

           public void onClick(View v) {
                if
                // TODO Auto-generated method stub

            }
        });*/





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


