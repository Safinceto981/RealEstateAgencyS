package com.example.realestateagencys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SellActivity extends AppCompatActivity {


    class Sofia extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sell_sofia);
        }
    }

    class Plovdiv extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sell_plovdiv);
        }
    }
    class Burgas extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sell_burgas);
        }
    }
    class Varna extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sell_varna);
        }
    }
    class VelikoTyrnovo extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sell_veliko_tyrnovo);
        }
    }
    class StaraZagora extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sell_stara_zagora);
        }
    }
}
