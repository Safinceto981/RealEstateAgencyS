package com.example.realestateagencys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUp extends AppCompatActivity {


    private EditText userName, userEmail,userPassword,phone,address;
    private Button btnReg;
    private TextView userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUpUIViews();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //database
                }
            }
        });
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,MainActivity.class));
            }
        });
    }
    private void setUpUIViews(){
        userName = (EditText)findViewById(R.id.enterUsername);
        userEmail= (EditText)findViewById(R.id.enterAddress);
        userPassword= (EditText)findViewById(R.id.enterPassword);
        phone= (EditText)findViewById(R.id.enterPhone);
        address= (EditText)findViewById(R.id.enterAddress);
        btnReg = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvLogin);
    }

    private Boolean validate(){
        Boolean result = false;
        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if (name.isEmpty() && password.isEmpty() && email.isEmpty()) {


            Toast.makeText(this,"Please enter Username,Email and password",Toast.LENGTH_SHORT).show();

        }else {
            result = true;

        }return result;

    }
}
