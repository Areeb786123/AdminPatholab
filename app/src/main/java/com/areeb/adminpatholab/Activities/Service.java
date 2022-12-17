package com.areeb.adminpatholab.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.areeb.adminpatholab.R;

public class Service extends AppCompatActivity {
    TextView paymentMode,userAddress,UserAppointmentDate,UserAppointmentTime,UserGender,otp,patientEmail,patientName,patientphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        otp= findViewById(R.id.otp);
        userAddress= findViewById(R.id.UserAddress);
        paymentMode= findViewById(R.id.PaymentMode);
        UserAppointmentDate = findViewById(R.id.UserAppointmentDate);
        UserAppointmentTime = findViewById(R.id.UserAppointmentTime);
        UserGender = findViewById(R.id.UserGender);
        patientEmail= findViewById(R.id.patientEmail);
        patientphone  = findViewById(R.id.patientPhone);
        patientName= findViewById(R.id.patientName);


        otp.setText("otp" + " - " +getIntent().getStringExtra("otp"));
        userAddress.setText(getIntent().getStringExtra("UserAddress"));
        paymentMode.setText(getIntent().getStringExtra("PaymentMode"));
        UserAppointmentDate.setText(getIntent().getStringExtra("UserAppointmentDate"));
//        UserAppointmentTime.setText(getIntent().getStringExtra("UserAppointmentTime"));
        UserGender.setText(getIntent().getStringExtra("UserGender"));
        patientphone.setText(getIntent().getStringExtra("patientPhone"));
        patientName.setText(getIntent().getStringExtra("patientName"));
        patientEmail.setText("Address" + " " +getIntent().getStringExtra("patientEmail"));

    }
}