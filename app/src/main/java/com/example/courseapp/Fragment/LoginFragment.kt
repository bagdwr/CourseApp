package com.example.courseapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.courseapp.MainActivity
import com.example.courseapp.R

class LoginFragment:Fragment() {
    private lateinit var signUpBtn:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_login,container,false)
        signUpBtn=view.findViewById(R.id.signUp)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpBtn.setOnClickListener {
            (requireActivity() as MainActivity)
                .changeFragment(RegistrationFragment())
        }
    }

}