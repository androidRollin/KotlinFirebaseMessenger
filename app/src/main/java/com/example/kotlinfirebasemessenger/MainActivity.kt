package com.example.kotlinfirebasemessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.kotlinfirebasemessenger.MainActivity.MainActivity.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var etUsernameRegister: EditText
    lateinit var etEmailRegister: EditText
    lateinit var etPasswordRegister: EditText
    lateinit var btnRegisterRegister: Button
    lateinit var tvAlreadyhaveanaccount: TextView

    private lateinit var auth: FirebaseAuth

    object MainActivity {
        const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsernameRegister = findViewById(R.id.etUsernameRegister)
        etEmailRegister = findViewById(R.id.etEmailRegister)
        etPasswordRegister = findViewById(R.id.etPasswordRegister)
        btnRegisterRegister = findViewById(R.id.btnRegisterRegister)
        tvAlreadyhaveanaccount = findViewById(R.id.tvAlreadyhaveaccount)







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