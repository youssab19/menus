package com.example.fragments.fragments


import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.fragment.app.Fragment


import androidx.navigation.fragment.findNavController

import com.example.fragments.view.Fragment1ViewModel

import com.example.fragments.R
import com.google.firebase.auth.FirebaseAuth


class Fragment1 : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var editTextEmail: String
    private lateinit var editTextPassword: String
    private lateinit var viewModel: Fragment1ViewModel
    private lateinit var app: Application


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        firebaseAuth = FirebaseAuth.getInstance()
        val view = inflater.inflate(R.layout.fragment_fragment1, container, false)
        val bt = view.findViewById<Button>(R.id.signup1)
        val bt1 = view.findViewById<Button>(R.id.login)
        val bt3 = view.findViewById<EditText>(R.id.editTextTextEmailAddress)
        val bt4 = view.findViewById<EditText>(R.id.editTextTextPassword)



        bt1.setOnClickListener {
            editTextEmail = bt3.text.toString().trim()
            editTextPassword = bt4.text.toString().trim()
            if (allField()) {
                firebaseAuth.signInWithEmailAndPassword(editTextEmail, editTextPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            findNavController().navigate(R.id.action_fragment1_to_fragment2)


                        } else {

                            Log.w(ContentValues.TAG, "createUserWithEmail:failure", it.exception)
                            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
            }
        }

        bt.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_signupFragment)

        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Fragment1ViewModel::class.java)


    }

    private fun allField(): Boolean {
        if (editTextEmail == "") {
            Toast.makeText(context, "email is empty", Toast.LENGTH_SHORT).show()

            return false
        }
        if (editTextPassword.length <= 6) {
            Toast.makeText(context, "passowrd less than 6 char", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

}
