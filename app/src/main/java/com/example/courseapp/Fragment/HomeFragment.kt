package com.example.courseapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.courseapp.MainActivity
import com.example.courseapp.R

class HomeFragment: Fragment() {
    private lateinit var btn: Button;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home,container,false)
        btn =view.findViewById(R.id.exit)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn.setOnClickListener {
            (requireActivity() as MainActivity)
                .changeFragment(LoginFragment())
        }
    }
}