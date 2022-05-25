package com.example.courseapp.Fragment

import android.content.SyncRequest
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
import com.example.courseapp.Query.UserInterface
import com.example.courseapp.R
import com.jaredsburrows.retrofit2.adapter.synchronous.SynchronousCallAdapterFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment:Fragment() {
    private lateinit var signUpBtn:TextView
    private lateinit var signInBtn:Button
    private lateinit var loginET:EditText
    private lateinit var passwordET:EditText
    private var retrofit: Retrofit?=null
    private var userInterface: UserInterface? = null;
    private val compositeDisposable= CompositeDisposable()


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
        retrofit=Retrofit.Builder()
            .baseUrl("http://192.168.233.185:8080/")
            .addCallAdapterFactory(SynchronousCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        userInterface = retrofit?.create(UserInterface::class.java)
        signUpBtn.setOnClickListener {
            (requireActivity() as MainActivity)
                .changeFragment(RegistrationFragment())
        }

        signInBtn.setOnClickListener {
            if(loginET.text.trim().isNotEmpty() && passwordET.text.trim().isNotEmpty()){
                val loginText = loginET.text.toString()
                val passwordText = passwordET.text.toString()

                val disposableGetWeatherInfo= Single.fromCallable{
                    userInterface?.getStatus(loginText,passwordText)
                }.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        user->user?.let {
                            Toast.makeText(requireContext(),it.toString(), Toast.LENGTH_LONG).show()
                    }
                    },{
                        Log.i("getWeather():","$it")
                        print(it.printStackTrace())
                        Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_LONG).show()
                    })

            }
        }


    }

}