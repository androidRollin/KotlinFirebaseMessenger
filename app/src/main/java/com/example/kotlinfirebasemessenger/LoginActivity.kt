package com.example.kotlinfirebasemessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.kotlinfirebasemessenger.LoginActivity.LoginActivity.TAG
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var etEmailLogin: EditText
    lateinit var etPasswordLogin: EditText
    lateinit var btnLogin: Button
    lateinit var tvBacktoregister: TextView


    object LoginActivity {
        const val TAG = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmailLogin = findViewById(R.id.etEmailLogin)
        etPasswordLogin = findViewById(R.id.etPasswordLogin)
        btnLogin = findViewById(R.id.btnLogin)
        tvBacktoregister = findViewById(R.id.tvBacktoregister)



        val email = etEmailLogin.text.toString()
        val password = etPasswordLogin.text.toString()


        btnLogin.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
               // .addOnCompleteListener {  }
               // .addOnFailureListener {  }
        }

        tvBacktoregister.setOnClickListener {
            Log.d(TAG, "Try to show login activity")

            //launch the login activity somehow
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }



    }
}