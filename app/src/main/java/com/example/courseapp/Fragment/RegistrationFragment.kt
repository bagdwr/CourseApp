package com.example.courseapp.Fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.courseapp.R

class RegistrationFragment:Fragment() {
    private lateinit var birthdayListener:TextView
    private lateinit var birthdayDP:DatePicker
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_register,container,false)

        birthdayListener=view.findViewById(R.id.birthdayListener)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        birthdayListener.setOnClickListener {
            showDialog()
        }
    }

    fun showDialog(){
        val dialog=Dialog(this.requireContext())
        dialog.setContentView(R.layout.birthday_dialog)
        birthdayDP=dialog.window!!.findViewById(R.id.birthdayDP)
        val saveBtn=dialog.window!!.findViewById<Button>(R.id.saveFromDialog)
        saveBtn.setOnClickListener {
            dialog.dismiss()
            birthdayListener.text=birthdayDP.dayOfMonth.toString()+"/"+birthdayDP.month.toString()+"/"+birthdayDP.year
        }

        dialog.show()
    }
}