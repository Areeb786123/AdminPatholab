package com.areeb.adminpatholab.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.areeb.adminpatholab.Activities.Editprofile;
import com.areeb.adminpatholab.Activities.Login;
import com.areeb.adminpatholab.Model.ProfileModel;
import com.areeb.adminpatholab.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;


public class profile extends Fragment {



    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databasereference ;
    ImageView profile_img;
    TextView userEmail,logout,username,userphone,userAddress;
    FloatingActionButton fab;
    FirebaseFirestore fstore;



    public profile() {
        // Required empty public constructor
    }

    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        fstore= FirebaseFirestore.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasereference = firebaseDatabase.getReference("Admins");
        fab = view .findViewById(R.id.fab);
        logout= view.findViewById(R.id.logout);
        username = view.findViewById(R.id.profile_name);
        userphone = view.findViewById(R.id.profile_phone);
        userAddress = view.findViewById(R.id.profile_address);

        userEmail = view.findViewById(R.id.profile_email);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent logout = new Intent(getActivity(),Login.class);
                startActivity(logout);

            }
        });

//        Query query = databasereference.orderByChild("email").equalTo(firebaseUser.getEmail());
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot Snapshot) {
//                for (DataSnapshot dataSnapshot1 :Snapshot.getChildren()) {
//                    // Retrieving Data from firebase
//                    String name = "" + dataSnapshot1.child("Fullname").getValue();
//                    String emaill = "" + dataSnapshot1.child("email").getValue();
//                    String image = "" + dataSnapshot1.child("image").getValue();
//                    // setting data to our text view
//                    username.setText(name);
//                    userEmail.setText(emaill);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        if(firebaseUser != null){
//
//
//            userEmail.setText(firebaseUser.getEmail().toString());
//            username.setText(firebaseUser.getDisplayName().toString());
//            userphone.setText(firebaseUser.getPhoneNumber().toString());
//            userAddress.setText("");
//
//
//        }else{
//            Toast.makeText(getContext(), "please login", Toast.LENGTH_SHORT).show();
//        }

        FirebaseUser admin_user = firebaseAuth.getCurrentUser();
       DocumentReference df = fstore.collection("Admins").document(admin_user.getUid());

       df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
           @Override
           public void onSuccess(DocumentSnapshot documentSnapshot) {

               if (documentSnapshot.exists()){
                   String name = documentSnapshot.getString("Fullname");
                   String phone = documentSnapshot.getString("Phone");
                   String email = documentSnapshot.getString("AdminEmail");
                   username.setText(name);
                   userphone.setText(phone);
                   userEmail.setText(email);
                   userAddress.setText(" ");
               }
           }
       });




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getActivity(), Editprofile.class));
            }
        });
        return  view;
    }
}