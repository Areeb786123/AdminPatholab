package com.areeb.adminpatholab.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.areeb.adminpatholab.MainActivity;
import com.areeb.adminpatholab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
EditText login_email,login_password;
TextView createAccount;
Button login_btn;
ProgressBar progress_bar;

FirebaseAuth  mAuth;
FirebaseUser currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_email= findViewById(R.id.Loginemail);
        login_password = findViewById(R.id.Loginpsw);
        login_btn = findViewById(R.id.Loiginbtn);
        progress_bar= findViewById(R.id.progress_bar);
        createAccount= findViewById(R.id.create_account);
        mAuth= FirebaseAuth.getInstance(FirebaseApp.getInstance());
        currentuser= FirebaseAuth.getInstance().getCurrentUser();

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create_account = new Intent(Login.this,Register.class);
                startActivity(create_account);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_fun();
            }
        });


    }

    public void login_fun(){

        progress_bar.setVisibility(View.VISIBLE);
        String email,password;
        email= login_email.getText().toString();
        password= login_password.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progress_bar.setVisibility(View.INVISIBLE);
                    Intent login_intent = new Intent(Login.this, MainActivity.class);
                    startActivity(login_intent);
                }else{
                    progress_bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "please register", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}