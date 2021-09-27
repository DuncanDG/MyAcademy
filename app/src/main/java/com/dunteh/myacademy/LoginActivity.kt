package com.dunteh.myacademy

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
//import android.R
import android.R.id

import android.R.id.button1
import android.os.Build
import android.view.View
import android.view.WindowManager


//Used to login the user into the application
// and transfer the user to the main activity


//Declare and initialize the variables to be used
private lateinit var email: EditText
private lateinit var password : EditText
private lateinit var login : Button
private lateinit var register : TextView
private lateinit var forgot : TextView

//declare the firebase auth
private lateinit var auth : FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //set the color of the statusbar
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(com.dunteh.myacademy.R.color.purple_500)
        }

        //Bring the views from the xml to the kotlin class

        email = findViewById(R.id.inputEmail)
        password = findViewById(R.id.inputPassword)
        login = findViewById(R.id.login)
        register = findViewById(R.id.login_register)
        forgot = findViewById(R.id.forgot)

        //create a firebaseAuth instance
        auth = FirebaseAuth.getInstance()

        //add a listener to the login button
        login. setOnClickListener {
//            login.setBackgroundResource(R.color.purple_200)
            loginUser()

        }

//        login.setOnClickListener(object : View.OnClickListener {
//                    override fun onClick(view: View?) {
//                        // set the color to relative layout
//                        login.setBackgroundResource(R.color.purple_200)
//                    }
//                })

        //add a listener to register
        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        //convert the text from the user to string
        val email = email.text.toString().trim()
        val password = password.text.toString().trim()

        //validate
        if (email.isEmpty()){
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show()
        }else if (password.isEmpty()){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show()
        }else{
            //to validate and login the user into te application we use the signInWithEmailAndPassword()
            //addOnCompleteListener adds a listener when the tasks completes
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}