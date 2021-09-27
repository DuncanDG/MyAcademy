package com.dunteh.myacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.annotation.NonNull




class Courses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        // --- adding the back button to the action bar -----
        // calling the action bar
        val actionBar: ActionBar? = supportActionBar

        // showing the back button in action bar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    // this event will enable the back
    // function to the button on press
//    For the difference :
//    actionBar.setHomeButtonEnabled(true) will just make the icon clickable, with the color at the background of the icon as a feedback of the click.
//    actionBar.setDisplayHomeAsUpEnabled(true) will make the icon clickable and add the < at the left of the icon.
      override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}