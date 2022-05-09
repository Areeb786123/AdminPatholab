package com.areeb.adminpatholab.Activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.areeb.adminpatholab.Adapters.TreatmentAdapter
import com.areeb.adminpatholab.Fragments.home
import com.areeb.adminpatholab.MainActivity
import com.areeb.adminpatholab.Model.TreatmentModel
import com.areeb.adminpatholab.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class AddTreatmentData : AppCompatActivity() {
    lateinit var add: Button
    lateinit var treatmentName: EditText
    lateinit var price : EditText
    lateinit var treatmentrec: RecyclerView
    lateinit var treatAdapter: TreatmentAdapter
    lateinit var treatmentList: ArrayList<TreatmentModel>
    lateinit var firestore: FirebaseFirestore
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser

    lateinit var AdminId: String
    lateinit var AdminEmail: String
    lateinit var itemname: TreatmentModel
    lateinit var  saveData: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_treatment_data)
        add = findViewById(R.id.addData);
        treatmentName = findViewById(R.id.treatmentName)
        treatmentrec = findViewById(R.id.recycleTreatment_main)

        saveData = findViewById(R.id.saveData)
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        price = findViewById(R.id.treatmentPrice)



//        saveData.isEnabled= false
//        saveData.isClickable= false
//        saveData.setBackgroundColor(Color.GRAY)
//
//        if(treatmentList.isEmpty()){
//            saveData.isClickable= false
//        }else{
//            saveData.isClickable= true
//        }
//
//        addItems()

        add.setOnClickListener {
            addEelement()
            addItems()
            treatmentName.text.clear();
            price.text.clear()

        }

        saveData.setOnClickListener {
            val intent :Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }


    //New Add functions
    public fun addEelement(){
        val df :DocumentReference = firestore.collection("Admins").document(firebaseAuth.uid.toString()).collection("Treatments").document();

        val setTreatmentData: HashMap<String, Any> = HashMap();

        setTreatmentData.put("treatmentName",treatmentName.text.toString())
        setTreatmentData.put("treatmentPrice",price.text.toString())
        df.set(setTreatmentData)

    }

    //This function is can save the treatment name in the recycler and then save it to database
    fun addItems() {
        treatmentrec.setHasFixedSize(true)
        treatmentrec.layoutManager = GridLayoutManager(this,2)

        val treatname = treatmentName.text
        Log.e("getname", treatname.toString())
//        treatmentList = ArrayList()
        add.setOnClickListener {

            itemname = TreatmentModel(treatname.toString())
            treatmentList.add(itemname)
            treatmentName.text.clear()
            treatAdapter = TreatmentAdapter(this, treatmentList)
            treatmentrec.adapter = treatAdapter
            treatAdapter.notifyDataSetChanged()


        }
        saveData.setOnClickListener {
            savingTreatmentDataToDataBase()
        }


    }



    //This function will store the data  of the treatment in the database
    fun savingTreatmentDataToDataBase() {


        val loggedAdmin: FirebaseUser = firebaseAuth.currentUser!!

        val df: DocumentReference =
            firestore.collection("Admins").document(loggedAdmin.uid).collection("Treatments")
                .document()

        val setTreatmentData: HashMap<String, Any> = HashMap();
        setTreatmentData.put("treatmentList", treatmentList)
        df.set(setTreatmentData)
        val treatmentDatasaved_intent : Intent = Intent (this, MainActivity::class.java)
        Toast.makeText(this,"data update", Toast.LENGTH_SHORT).show()
        startActivity(treatmentDatasaved_intent)


    }
}