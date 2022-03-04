package com.areeb.adminpatholab.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.areeb.adminpatholab.MainActivity;
import com.areeb.adminpatholab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    FirebaseUser admin_user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        admin_user = FirebaseAuth.getInstance().getCurrentUser();

        if(admin_user != null){
            Intent splash_intent = new Intent(Splash.this, MainActivity.class);
            startActivity(splash_intent);
        }else{
            Toast.makeText(getApplicationContext(), "please login", Toast.LENGTH_SHORT).show();
            Intent not_login = new Intent(Splash.this,Login.class);
            startActivity(not_login);
        }
    }
}