package com.areeb.adminpatholab.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.areeb.adminpatholab.MainActivity
import com.areeb.adminpatholab.Model.ProfileModel
import com.areeb.adminpatholab.R
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*
import kotlin.collections.HashMap


class UploadImage : AppCompatActivity() {


    //init for varaiables
    lateinit var choose_image: Button
    private val PICKPHOTOCODE = 100;
    private var photoUri: Uri? = null
    lateinit var uploadImage: Button
    lateinit var ViewProfileImage: ImageView;

    //init of firebase

    lateinit var storage: FirebaseStorage
    lateinit var fstore: FirebaseFirestore
    lateinit var storageReference: StorageReference
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)
        choose_image = findViewById(R.id.RegisterChooseImage);
        ViewProfileImage = findViewById(R.id.ViewprofileImage)
        uploadImage = findViewById(R.id.RegisterUploadImage);


        //declaration of firebase
        firebaseAuth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()
        storageReference = FirebaseStorage.getInstance().getReference()


        choose_image.setOnClickListener {
            SelectImage()

        }

        uploadImage.setOnClickListener {

            UploadSelectedImage()
        }


    }


    fun SelectImage() {

        val chooseImageIntent = Intent(Intent.ACTION_GET_CONTENT)
        chooseImageIntent.type = "image/*"

        startActivityForResult(chooseImageIntent, PICKPHOTOCODE)


    }

    //handling the image choose

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICKPHOTOCODE && resultCode == RESULT_OK && data != null) {
            photoUri = data?.data
            ViewProfileImage.setImageURI(photoUri)

        } else {
            Toast.makeText(this, "some error occur while handling pic", Toast.LENGTH_SHORT).show();
        }
    }

    fun UploadSelectedImage() {


        if (photoUri == null) {


            Toast.makeText(this, "please select photo", Toast.LENGTH_SHORT).show()
        }
        val pd = ProgressDialog(this)
        pd.setTitle("Loading ....⌛")
        pd.show();

        val photoUrl = photoUri as Uri

        val photoRef = storageReference.child("AdminPic/${System.currentTimeMillis()}-photo.jpg")


        //upload firebase storage
        photoRef.putFile(photoUrl).continueWithTask { photoUploadTask ->
            photoRef.downloadUrl

        }.continueWith { downloadUrlTask ->

            val currentUser = firebaseAuth.currentUser
            Log.e("downloadTaskx", downloadUrlTask.toString());
            Toast.makeText(this, downloadUrlTask.toString(), Toast.LENGTH_SHORT).show()


            val df: DocumentReference = fstore.collection("Admins").document(currentUser!!.uid)

            val hashmap: HashMap<String, Any> = HashMap<String, Any>();
            hashmap.put("ProfilePic", downloadUrlTask.toString());
            df.update(hashmap)


            pd.dismiss()

            val home_intent = Intent(this, Login::class.java)
            startActivity(home_intent)


            val new_in = Intent(applicationContext, Login::class.java)
            startActivity(new_in)


        }


    }


}