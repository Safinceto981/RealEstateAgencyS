package com.example.realestateagencys;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView Warning;
    private Button Login;
    private Button Register;

    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = (EditText)findViewById(R.id.enterUsername);
        Password = (EditText)findViewById((R.id.enterPassword));
        Warning = (TextView)findViewById(R.id.incorrectAttempts);
        Login = (Button)findViewById(R.id.btnLogin);
        Register= (Button)findViewById(R.id.btnRegister) ;

        Warning.setText("No of attempts remaining:5");

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validate(Username.getText().toString(),Password.getText().toString());

            }
        });


    }
    private void validate(String userName,String userPassword ){
        if((userName.equals( "Admin"))&&(userPassword.equals("1234"))){

            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }else{
            counter--;
            Warning.setText("No of attempts remaining: "+String.valueOf(counter ));
            if(counter==0){
                Login.setEnabled(false);
            }
        }

    }
}
