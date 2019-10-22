package com.example.realestateagencys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        login = (Button)findViewById(R.id.btnLoginSignUp);



      login.setOnClickListener(new View.OnClickListener() {

          public void onClick(View v) {
              startActivity(new Intent(SecondActivity.this, MainActivity.class));
          }
      });
    }
}
