package com.dunteh.myacademy

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.*

//This is the main activity of the application
     //The user will be directed to this activity after a successful login or creation of an account

private lateinit var courses : RelativeLayout
private lateinit var profile : RelativeLayout
private lateinit var email : TextView
private lateinit var logout: ImageView
private lateinit var lt : Button
val dbemail : String = ""

//declare the Databasereference the path to where the info is that we want to get
    private lateinit var database: DatabaseReference

    //declare the firebase auth
    private lateinit var auth : FirebaseAuth
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#30000000")))

        //initialize FibaseAuth create its instance
        auth = FirebaseAuth.getInstance()

        //create an instance of the databasereference
        //we want to get data from the users node
        database = FirebaseDatabase.getInstance().getReference("users")

        email = findViewById(R.id.email)

        //check if the user is logged in or not
        if(auth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }

        courses = findViewById(R.id.courses_rl)


        courses.setOnClickListener {
            val Intent = Intent(this, Courses::class.java)
            startActivity(Intent)
        }

        profile = findViewById(R.id.profile_rl)

        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

            logout = findViewById(R.id.logout)

            logout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "Logged Out", Toast.LENGTH_LONG).show()
            }
        }

        //add an addValueListener inorder to read data from the path chosen
        database.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists() && snapshot == auth.currentUser){
                  var dbemail = snapshot.getValue().toString()
                          email.setText(dbemail)
                }
            }
        })
    }

}

private fun FirebaseAuth.signOut(mainActivity: MainActivity) {

}
