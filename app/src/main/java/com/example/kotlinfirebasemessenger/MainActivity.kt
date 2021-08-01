package com.example.kotlinfirebasemessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.kotlinfirebasemessenger.MainActivity.MainActivity.TAG

class MainActivity : AppCompatActivity() {

    lateinit var etUsernameRegister: EditText
    lateinit var etEmailRegister: EditText
    lateinit var etPasswordRegister: EditText
    lateinit var btnRegisterRegister: Button
    lateinit var tvAlreadyhaveanaccount: TextView

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

        val username = etUsernameRegister.text.toString()
        val email = etEmailRegister.text.toString()
        val password = etPasswordRegister.text.toString()


        //Firebase Authentication to create a user with email and password


        btnRegisterRegister.setOnClickListener {
            Log.d(TAG, "Email is: $email")
            Log.d(TAG, "Username is: $username")
            Log.d(TAG, "Password is: $password")
        }

        tvAlreadyhaveanaccount.setOnClickListener {
            Log.d(TAG, "Try to show login activity")

            //launch the login activity somehow
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }




    }


}