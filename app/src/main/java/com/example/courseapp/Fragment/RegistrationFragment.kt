package com.example.courseapp.Fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.courseapp.MainActivity

import com.example.courseapp.Model.Users
import com.example.courseapp.R
import com.google.gson.Gson
import kotlin.random.Random

class RegistrationFragment:Fragment() {
    private lateinit var birthdayListener:TextView
    private lateinit var birthdayDP:DatePicker
    private lateinit var loginET:EditText
    private lateinit var passwordRegET:EditText
    private lateinit var fullnameET:EditText
    private lateinit var registerBtn:Button
    private lateinit var backView : ImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_register,container,false)

        birthdayListener=view.findViewById(R.id.birthdayListener)
        loginET =view.findViewById(R.id.loginET)
        loginET = view.findViewById(R.id.loginET)
        passwordRegET = view.findViewById(R.id.passwordRegET)
        fullnameET = view.findViewById(R.id.fullnameET)
        registerBtn = view.findViewById(R.id.registerBtn)
        backView = view.findViewById(R.id.imageView);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        birthdayListener.setOnClickListener {
            showDialog()
        }

        backView.setOnClickListener(View.OnClickListener {
            (requireActivity() as MainActivity)
                .changeFragment(LoginFragment())
        })

        registerBtn.setOnClickListener {
            val user= Users(Random.nextLong(100),fullnameET.text.toString(),loginET.text.toString(),birthdayListener.text.toString(), passwordRegET.text.toString())
            val gson = Gson()
            val userGson = gson.toJson(user)
            Log.i("RegFragment: ", userGson)

        }
    }

    fun showDialog(){
        val dialog=Dialog(this.requireContext())
        dialog.setContentView(R.layout.birthday_dialog)
        birthdayDP=dialog.window!!.findViewById(R.id.birthdayDP)

        val saveBtn=dialog.window!!.findViewById<Button>(R.id.saveFromDialog)
        saveBtn.setOnClickListener {
            dialog.dismiss()

            birthdayListener.text=
                birthdayDP.dayOfMonth.toString()+"/"+birthdayDP.month.toString()+"/"+birthdayDP.year

        }

        dialog.show()
    }





//    TODO strelka to login fragment
//    TODO datepicker set Max date Now
}