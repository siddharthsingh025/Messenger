package com.siddharth.massengerapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.siddharth.massengerapp.Models.Users;
import com.siddharth.massengerapp.R;
import com.siddharth.massengerapp.databinding.ActivitySignUpBinding;

public class SinghUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        getSupportActionBar().hide();

        progressDialog = new ProgressDialog(SinghUpActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!binding.txtUsername.getText().toString().isEmpty() && !binding.txtEmail.getText().toString().isEmpty() && !binding.txtPassword.getText().toString().isEmpty()) {

                    auth.createUserWithEmailAndPassword(binding.txtEmail.getText().toString(), binding.txtPassword.getText().toString())  // creating a account using email and password
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        Users user = new Users(binding.txtUsername.getText().toString(), binding.txtEmail.getText().toString(), binding.txtPassword.getText().toString()); // combine the values into model class
                                        String id = task.getResult().getUser().getUid();// abstract uid from firebase task variable
                                        database.getReference().child("Users").child(id).setValue(user); // now we create child of database and set value to realtime data base for all unique uIds

                                        Intent intent = new Intent(SinghUpActivity.this, MainActivity.class);
                                        startActivity(intent);

                                        Toast.makeText(SinghUpActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SinghUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }

                                }

                            });

                } else {
                    Toast.makeText(SinghUpActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }
        });




        binding.txtAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , LogInActivity.class);
                startActivity(intent);
            }
        });



    }

}