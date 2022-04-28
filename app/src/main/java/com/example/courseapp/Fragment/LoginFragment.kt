package com.example.courseapp.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.courseapp.MainActivity
import com.example.courseapp.Model.User
import com.example.courseapp.MySharedPreference
import com.example.courseapp.R
import com.google.gson.Gson




class LoginFragment:Fragment() {
    private lateinit var signUpBtn:TextView
    private lateinit var signInBtn:Button
    private lateinit var loginET:EditText
    private lateinit var passwordET:EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_login,container,false)
        signUpBtn=view.findViewById(R.id.signUp)
        signInBtn = view.findViewById(R.id.signInBtn)
        loginET = view.findViewById(R.id.loginET)
        passwordET = view.findViewById(R.id.passwordET)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpBtn.setOnClickListener {
            (requireActivity() as MainActivity)
                .changeFragment(RegistrationFragment())
        }

        signInBtn.setOnClickListener {
            val mySharedPreference = MySharedPreference.getSharedPreference(requireActivity().application)
            val gson = Gson()
            val json: String = mySharedPreference.getString(MySharedPreference.KEY,"")?:""
            val user: User = gson.fromJson(json, User::class.java)

            Log.i("LoginFragment: ", json)
            if(user.login.equals(loginET.text.toString()) && user.password.equals(passwordET.text.toString())){
                Toast.makeText(this.context,"${user.fullname} welcome", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this.context,"Wrong action", Toast.LENGTH_SHORT).show()
            }

        }


    }

}