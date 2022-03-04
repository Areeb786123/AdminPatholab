package com.areeb.adminpatholab.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.UpdateLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.areeb.adminpatholab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText reg_email,reg_pass,regname,regphone,regAddress;
    Button register_btn;
    FirebaseAuth mAuth;
    ProgressBar progress_bar ;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_email = findViewById(R.id.regemail);
  reg_pass = findViewById(R.id.reg_pas);
  regname= findViewById(R.id.regname);
  regphone = findViewById(R.id.regphone);
  regAddress = findViewById(R.id.AdminAddress);

       register_btn = findViewById(R.id.reg_btn);
       progress_bar = findViewById(R.id.progress_bar);
       mAuth = FirebaseAuth.getInstance(FirebaseApp.getInstance());
       fstore = FirebaseFirestore.getInstance();

       register_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               register_fun();
           }
       });


    }



    //old register function
    void register_fun(){
        progress_bar.setVisibility(View.VISIBLE);

        String email, password ;
        email = reg_email.getText().toString();
        password= reg_pass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "email filled is empty", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"password field is empty",Toast.LENGTH_SHORT).show();

        }
        Toast.makeText(this, "phase 1 ", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Toast.makeText(Register.this, "pahse 2 ", Toast.LENGTH_SHORT).show();
                if(task.isSuccessful()){

                    progress_bar.setVisibility(View.INVISIBLE);
                    FirebaseUser admin_user = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), "Sucessfully register", Toast.LENGTH_SHORT).show();
                    DocumentReference df = fstore.collection("Admins").document(admin_user.getUid());
                    HashMap<String,Object> adminInfo = new HashMap<>();
                    adminInfo.put("Fullname",regname.getText().toString());
                    adminInfo.put("AdminEmail",reg_email.getText().toString());
                    adminInfo.put("Phone",regphone.getText().toString());
                    adminInfo.put("id",mAuth.getCurrentUser().getUid());
                    adminInfo.put("AdminAddress",regAddress.getText().toString());
                    adminInfo.put("isAdmin" , "0");
                    df.set(adminInfo);
                    Int();


                }else{
                    Toast.makeText(getApplicationContext(), "some error form", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    void Int(){




            Intent register_intent = new Intent(Register.this, UploadImage.class);
            startActivity(register_intent);

    }
}