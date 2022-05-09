package com.areeb.adminpatholab.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.areeb.adminpatholab.Adapters.TreatmentAdapter
import com.areeb.adminpatholab.MainActivity
import com.areeb.adminpatholab.Model.TreatmentModel
import com.areeb.adminpatholab.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.properties.Delegates


class treatset : Fragment() {
    lateinit var add: Button
    lateinit var treatmentrec: RecyclerView
    lateinit var treatAdapter: TreatmentAdapter
    lateinit var treatmentList: ArrayList<TreatmentModel>
    lateinit var firestore: FirebaseFirestore
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser
    lateinit var Adminname: String
    lateinit var AdminId: String
    lateinit var AdminEmail: String
    var amount by Delegates.notNull<Int>()

    lateinit var  updatename :EditText
    lateinit var  updatePrice :EditText
    lateinit var itemname: TreatmentModel
    lateinit var  finaleupdate:Button

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_treatset, container, false)
        add = root.findViewById(R.id.addData);
        updatename = root.findViewById(R.id.updateName)
        updatePrice= root.findViewById(R.id.updateprice);

        treatmentrec = root.findViewById(R.id.recycleTreatment)
        finaleupdate = root.findViewById(R.id.finaleupdate)
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

//        UpdateItems()

        add.setOnClickListener {
            NewUpdateFun()
            updatename.text.clear()
            updatePrice.text.clear()

        }
//        finaleupdate.setOnClickListener {
//            done()
//        }

        return root
    }

    //Functions start here

    //This function is can save the treatment name in the recycler and then save it to database
//    fun UpdateItems() {
//        treatmentrec.setHasFixedSize(true)
//        treatmentrec.layoutManager = GridLayoutManager(activity,2)
//
//        val treatname = add.text
//        Log.e("getname", treatname.toString())
//        treatmentList = ArrayList()
//        add.setOnClickListener {
//
//            itemname = TreatmentModel(treatname.toString())
//            treatmentList.add(itemname)
//            treatmentName.text.clear()
//            treatAdapter = TreatmentAdapter(requireContext(), treatmentList)
//            treatmentrec.adapter = treatAdapter
//            treatAdapter.notifyDataSetChanged()
//
//
//        }
//        saveData.setOnClickListener {
//           UpdatingTreatmentDataToDataBase()
//        }
//
//
//    }

    //This function will store the data  of the treatment in the database
//    fun UpdatingTreatmentDataToDataBase() {
//        val loggedAdmin: FirebaseUser = firebaseAuth.currentUser!!
//
//        val df: DocumentReference =
//            firestore.collection("Admins").document(loggedAdmin.uid).collection("Treatments")
//                .document()
//
//        val setTreatmentData: HashMap<String, Any> = HashMap();
//        setTreatmentData.put("treatmentList", treatmentList)
//        df.update(setTreatmentData)
//        val treatmentDatasaved_intent :Intent = Intent (activity,home::class.java)
//        Toast.makeText(context,"data update",Toast.LENGTH_SHORT).show()
//        startActivity(treatmentDatasaved_intent)
//
//
//    }

    fun NewUpdateFun(){
        val df :DocumentReference = firestore.collection("Admins").document(firebaseAuth.uid.toString()).collection("Treatments").document();

        val setTreatmentData: HashMap<Any, Any> = HashMap();


        setTreatmentData.put("treatmentName",updatename.text.toString())
        setTreatmentData.put("treatmentPrice",updatePrice.text.toString().toInt())
        df.set(setTreatmentData)

    }
//    fun done(){
//        val newINTENT = Intent(context,home::class.java)
//        startActivity(newINTENT)
//    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment treatset.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            treatset().apply {
                arguments = Bundle().apply {

                }
            }
    }
}

