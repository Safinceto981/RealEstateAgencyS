package com.example.realestateagencys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RentActivity extends AppCompatActivity {

     class Sofia extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rent_sofia);
        }

    }
    class Plovdiv extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rent_plovdiv);
        }

    }
    class Varna extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rent_varna);
        }

    }
    class Burgas extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rent_burgas);
        }

    }
    class VelikoTyrnovo extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rent_veliko_tyrnovo);
        }

    }
    class StaraZagora extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rent_stara_zagora);
        }

    }
}
