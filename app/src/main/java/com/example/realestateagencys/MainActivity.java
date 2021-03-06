package com.example.realestateagencys;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestateagencys.ui.admin.AddArea;
import com.example.realestateagencys.ui.view.NearbyAreas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView Warning;
    private Button Login;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private TextView forgotPassword;


    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Username = (EditText) findViewById(R.id.enterUsername);
        Password = (EditText) findViewById((R.id.enterPassword));
        Warning = (TextView) findViewById(R.id.incorrectAttempts);
        Login = (Button) findViewById(R.id.btnLogin);

        userRegistration = (TextView) findViewById(R.id.tvRegister);

        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        Warning.setText("No of attempts remaining:5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
           finish();

            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(), Password.getText().toString());

            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });

    }



    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("It is gonna take some seconds");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    //  Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();



                    checkEmailVerification();
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Warning.setText("No of attempts remaining: " + counter);
                    progressDialog.dismiss();
                    if (counter == 0) {
                        Login.setEnabled(false);
                    }
                }
            }

        });


    }


    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();



        if (emailflag) {
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));

        } else {
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

}




