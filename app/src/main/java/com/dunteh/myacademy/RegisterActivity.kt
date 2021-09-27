package com.dunteh.myacademy

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase

//Used to register user into the application
//takes user to the main activity after successful registration

//declare the variables to be used
private lateinit var register_btn: Button
private lateinit var password: EditText
private lateinit var email : EditText
private var userId:String?=null
private lateinit var username : EditText
private lateinit var already : TextView
private lateinit var progressBar: ProgressBar

//1st you have to declare an instance of firebaseAuth
private lateinit var auth: FirebaseAuth

//the databasereference
private lateinit var ref: DatabaseReference

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //set the color of the statusbar
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.purple_500)
        }

        //get the views from xml to kotlin class
        register_btn = findViewById(R.id.register)
        password = findViewById(R.id.inputPassword)
        email = findViewById(R.id.inputEmail)
        already = findViewById(R.id.textView)
        progressBar = findViewById(R.id.progressBar)

        //initialize the firebaseAuth instance
        auth = FirebaseAuth.getInstance()

        //add an listener to the register button
        register_btn.setOnClickListener {
            registerUser()
        }

        //add a listener to already to take you back to the loginactivity
        already.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser() {
        //convert the data got from the user to string
        var email = email.text.toString().trim()
        val password = password.text.toString().trim()

        //validate the details
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_LONG).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show()
        } else if (password.length<3){
            Toast.makeText(applicationContext,"Password is too short",Toast.LENGTH_LONG).show()
        } else{

            //Making progressBar visible
            progressBar!!.visibility= View.VISIBLE

            //we can take the help of createUserWithEmailAndPassword() method to register a user
            //This method takes email and password as a parameter validates them and then creates a new user
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        progressBar.visibility=View.GONE

                        //Getting reference to ?users? node
//                        mFirebaseDatabase=mFirebaseDatabaseInstances!!.getReference("users")
                        ref = FirebaseDatabase.getInstance().getReference("users")

                        //Getting current user from FirebaseAuth
                        val user = FirebaseAuth.getInstance().currentUser

                        //add username, email to database
                        userId=user!!.uid
                        email= user.email.toString()

                        //Creating a new user
//                        val myUser = User(username.text.toString(),email)

                        //Writing data into database using setValue() method
//                        ref!!.child(userId!!).setValue(myUser)

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Registration failed", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }
}

