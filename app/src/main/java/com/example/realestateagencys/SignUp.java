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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class SignUp extends AppCompatActivity {


    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userPhone;
    private EditText userAddress;
    private EditText userAge;

    private Button btnReg;
    private TextView userLogin;

    private FirebaseAuth firebaseAuth;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    String email,name,age,password,address,phone;
    private  DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUpUIViews();
        myRef= FirebaseDatabase.getInstance().getReference().child("user");

        firebaseAuth = FirebaseAuth.getInstance();
      //  firebaseStorage= FirebaseStorage.getInstance();
        //storageReference  = firebaseStorage.getReference();
       // StorageReference myRef = storageReference.child(firebaseAuth.getUid()).getParent();

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

                                sendEmailVerification();

                                Toast.makeText(SignUp.this,"Registration successful",Toast.LENGTH_SHORT).show();
                                finish();
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
        userPhone= (EditText)findViewById(R.id.enterPhone);
        userAddress= (EditText)findViewById(R.id.enterAddress);
        userAge=(EditText)findViewById(R.id.etAge);
        btnReg = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvLogin);

    }

    private Boolean validate(){
        Boolean result = false;

        name = userName.getText().toString();
        email = userEmail.getText().toString();
        password = userPassword.getText().toString();
        age = userAge.getText().toString();
        address = userAddress.getText().toString();
        phone = userPhone.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()||age.isEmpty()||address.isEmpty()||phone.isEmpty()) {


            Toast.makeText(this,"Please enter all details",Toast.LENGTH_SHORT).show();

        }else {
            result = true;

        }return result;


    }
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(SignUp.this,"Verification email sent",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(SignUp.this,MainActivity.class));

                    }else {
                        Toast.makeText(SignUp.this,"Verification has not been sent",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());



        UserProfile userProfile= new UserProfile(age,email,name,phone,address);
        myRef.setValue(userProfile);

    }
}
