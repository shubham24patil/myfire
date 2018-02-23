package com.example.saurabh.myfire;

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

public class MainActivity extends AppCompatActivity {

    Button btLogin,btRegister;
    EditText etEmail,etPass,etname;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth =FirebaseAuth.getInstance();

        btLogin = (Button) findViewById(R.id.btllogin);
        btRegister = (Button) findViewById(R.id.btlRegister);

        etname = (EditText) findViewById(R.id.eTLname);
        etEmail = (EditText) findViewById(R.id.eTLemail);
        etPass = (EditText) findViewById(R.id.eTLpassword);




        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));


            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signin();
            }
        });

    }

    private void signin() {

        final String email,pass,name;

        name = etname.getText().toString();
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();

        if(TextUtils.isEmpty(email)) {

            Toast.makeText(MainActivity.this,"Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass)) {

            Toast.makeText(MainActivity.this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {

                    Toast.makeText(MainActivity.this,"Welcome"+name,Toast.LENGTH_SHORT).show();
                }
                else

                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
            }
        });



    }
}
