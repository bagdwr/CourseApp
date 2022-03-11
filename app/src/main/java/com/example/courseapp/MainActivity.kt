package com.example.courseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.courseapp.Fragment.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(LoginFragment())
    }

    fun changeFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentId,fragment)
            .addToBackStack(null)
            .commit()
    }
}