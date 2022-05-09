package com.areeb.adminpatholab.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.FrameStats;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.areeb.adminpatholab.Activities.Service;
import com.areeb.adminpatholab.Adapters.HomeAdapter;
import com.areeb.adminpatholab.Adapters.TreatmentAdapter;
import com.areeb.adminpatholab.Model.HomeModel;
import com.areeb.adminpatholab.Model.TreatmentModel;
import com.areeb.adminpatholab.R;
import com.areeb.adminpatholab.clickListner.HomeListner;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment implements HomeListner {

    FirebaseFirestore fstore;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
HomeAdapter treatmentAdapter;
    ArrayList<HomeModel> treatmentList;
    RecyclerView homrec;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        homrec = view.findViewById(R.id.homerec);
        homrec.setHasFixedSize(true);
        homrec.setLayoutManager(new LinearLayoutManager(getContext()));
        treatmentList = new ArrayList<>();

        treatmentAdapter = new HomeAdapter(treatmentList,getContext(),this);

        homrec.setAdapter(treatmentAdapter);
        getAllTask();
        return view;
    }


    public void getAllTask(){

        FirebaseUser currentUser= firebaseAuth.getCurrentUser();


fstore.collection("Admins").document(currentUser.getUid().toString()).collection("AdminAppointmentSection").addSnapshotListener(new EventListener<QuerySnapshot>() {
    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


        for(DocumentChange dc : value.getDocumentChanges()){
            if(dc.getType()== DocumentChange.Type.ADDED){

                treatmentList.add(dc.getDocument().toObject(HomeModel.class));

            }
            treatmentAdapter.notifyDataSetChanged();
        }
    }
});
    }

    @Override
    public void onHomeItemClicked(HomeModel homeModelList) {
        Intent passData = new Intent(getContext(), Service.class);
        passData.putExtra("PaymentMode",homeModelList.getPaymentmode());
        passData.putExtra("UserAddress",homeModelList.getUserAddress());
        passData.putExtra("UserAppointmentDate",homeModelList.getUserAppointmentDate());
//        passData.putExtra("UserAppointmentTime",homeModelList.getUserAppointmentTime());
        passData.putExtra("UserGender",homeModelList.getUserGender());
        passData.putExtra("otp",homeModelList.getOtp());
        passData.putExtra("patientName",homeModelList.getPatientName());
        passData.putExtra("patientEmail",homeModelList.getPatientEmail());
        passData.putExtra("patientPhone",homeModelList.getPatientPhone());
        startActivity(passData);

    }
}