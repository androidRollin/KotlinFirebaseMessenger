package com.example.kotlinfirebasemessenger

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.kotlinfirebasemessenger.RegisterActivity.MainActivity.TAG
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var etUsernameRegister: EditText
    lateinit var etEmailRegister: EditText
    lateinit var etPasswordRegister: EditText
    lateinit var btnRegisterRegister: Button
    lateinit var tvAlreadyhaveanaccount: TextView
    lateinit var ibSelectPhotoRegister: ImageButton

    private lateinit var auth: FirebaseAuth

    object MainActivity {
        const val TAG = "RegisterActivity"
    }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsernameRegister = findViewById(R.id.etUsernameRegister)
        etEmailRegister = findViewById(R.id.etEmailRegister)
        etPasswordRegister = findViewById(R.id.etPasswordRegister)
        btnRegisterRegister = findViewById(R.id.btnRegisterRegister)
        tvAlreadyhaveanaccount = findViewById(R.id.tvAlreadyhaveaccount)
        ibSelectPhotoRegister = findViewById(R.id.ibSelectphotoRegister)


        ibSelectPhotoRegister.setBackgroundResource(R.drawable.indiana)
        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
                if (data != null){
                    Log.d(TAG, "Photo was selected")
                    val uri = data.data
                    Log.d(TAG, uri.toString())
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    val bitmapDrawable = BitmapDrawable(bitmap)

                    if (Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
                        ibSelectPhotoRegister.setBackgroundDrawable(bitmapDrawable)
                    }
                    else
                    {
                        ibSelectPhotoRegister.background = bitmapDrawable
                    }
//                    Log.d(TAG, uri.toString())
//                    val bitmap = getCapturedImage(uri!!)
//                    val bitmapDrawable = BitmapDrawable(this.resources, bitmap)
//                    btnSelectPhotoRegister.background = bitmapDrawable

                }
            }
        }

        ibSelectPhotoRegister.setOnClickListener {
            Log.d(TAG, "Try to show photo selector")



            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            resultLauncher.launch(intent)

        }


        btnRegisterRegister.setOnClickListener {
            performRegister()
        }

        tvAlreadyhaveanaccount.setOnClickListener {
            Log.d(TAG, "Try to show login activity")

            //launch the login activity somehow
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun getCapturedImage(selectedPhotoUri: Uri): Bitmap {
        return when {
            Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
                contentResolver,
                selectedPhotoUri
            )
            else -> {
                val source = ImageDecoder.createSource(contentResolver, selectedPhotoUri)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }

    private fun performRegister() {
        val username = etUsernameRegister.text.toString()
        val email = etEmailRegister.text.toString()
        val password = etPasswordRegister.text.toString()

        Log.d(TAG, "Email is: $email")
        Log.d(TAG, "Username is: $username")
        Log.d(TAG, "Password is: $password")

        if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {

            Toast.makeText(this, "Please enter text in email/username/pw", Toast.LENGTH_SHORT).show()
            return
        }

        //Firebase Authentication to create a user with email and password
        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                // else if successful
                Log.d(TAG, "Successfully created user with uid: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }


}