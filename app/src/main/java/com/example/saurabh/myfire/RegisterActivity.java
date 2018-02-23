package com.example.saurabh.myfire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail,etPass;
    Button btRegister;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        etEmail = (EditText) findViewById(R.id.etRemail);
        etPass = (EditText) findViewById(R.id.etRpass);
        btRegister = (Button) findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           UserRegister();
            }


        });
    }
    private void UserRegister() {

        String email,pass;
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){

            Toast.makeText(RegisterActivity.this,"Some fields are empty",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registring your account please wait....");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {

                    Toast.makeText(RegisterActivity.this,"Registration successful",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(RegisterActivity.this,"Registration error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void LoginPage(View view) {

        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
    }
}
