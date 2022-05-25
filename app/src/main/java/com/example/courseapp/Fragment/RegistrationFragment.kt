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
import com.example.courseapp.Query.UserInterface
import com.example.courseapp.R
import com.google.gson.Gson
import com.jaredsburrows.retrofit2.adapter.synchronous.SynchronousCallAdapterFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class RegistrationFragment:Fragment() {
    private lateinit var birthdayListener:TextView
    private lateinit var birthdayDP:DatePicker
    private lateinit var loginET:EditText
    private lateinit var passwordRegET:EditText
    private lateinit var fullnameET:EditText
    private lateinit var registerBtn:Button
    private lateinit var backView : ImageView

    private var retrofit: Retrofit?=null
    private var userInterface: UserInterface? = null;
    private val compositeDisposable= CompositeDisposable()


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
        retrofit=Retrofit.Builder()
            .baseUrl("http://192.168.233.185:8080/")
            .addCallAdapterFactory(SynchronousCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        userInterface = retrofit?.create(UserInterface::class.java)

        birthdayListener.setOnClickListener {
            showDialog()
        }

        backView.setOnClickListener(View.OnClickListener {
            (requireActivity() as MainActivity)
                .changeFragment(LoginFragment())
        })

        registerBtn.setOnClickListener {
            if(loginET.text.trim().isNotEmpty() && fullnameET.text.trim().isNotEmpty()
                && birthdayListener.text.trim().isNotEmpty() && passwordRegET.text.trim().isNotEmpty()){
                val login = loginET.text.toString()
                val fullname = fullnameET.text.toString()
                val password = passwordRegET.text.toString()
                val birthday = birthdayListener.text.toString()

                val disposableGetWeatherInfo= Single.fromCallable{
                    userInterface?.registerUser(login,password,fullname,birthday)
                }.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                            status->status?.let {
                            if(it){
                                loginET.text.clear()
                                fullnameET.text.clear()
                                passwordRegET.text.clear()
                                birthdayListener.text=""
                                Toast.makeText(requireContext(),"Successfully registered", Toast.LENGTH_LONG).show()
                            }else{
                                Toast.makeText(requireContext(),"Something went wrong", Toast.LENGTH_LONG).show()
                            }
                    }
                    },{
                        Log.i("registerUser():","$it")
                        print(it.printStackTrace())
                        Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_LONG).show()
                    })
            }
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