package com.example.realestateagencys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends AppCompatActivity {


    private EditText userName, userEmail,userPassword,phone,address;
    private Button btnReg;
    private TextView userLogin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUpUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){


                            Toast.makeText(SignUp.this,"Registration successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this,MainActivity.class));
                        }else
                            {Toast.makeText(SignUp.this,"Registration unsuccessful",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
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
        userName = (EditText)findViewById(R.id.enterUserName);
        userEmail= (EditText)findViewById(R.id.enterEmail);
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

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {


            Toast.makeText(this,"Please enter Username,Email and password",Toast.LENGTH_SHORT).show();

        }else {
            result = true;

        }return result;

    }
}
