package com.example.fragments.fragments


import android.content.ContentValues.TAG

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import com.example.fragments.R
import com.example.fragments.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignupFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var editTextEmail: String
    private lateinit var editTextPassword: String
    private lateinit var editTextName: String
    private lateinit var editTextPhone: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        firebaseAuth = Firebase.auth
        firebaseAuth = FirebaseAuth.getInstance()


        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        val bt1 = view.findViewById<EditText>(R.id.editTextTextPersonName)
        val bt2 = view.findViewById<EditText>(R.id.editTextPhone)
        val bt3 = view.findViewById<EditText>(R.id.editTextTextEmailAddress)
        val bt4 = view.findViewById<EditText>(R.id.editTextTextPassword)

        editTextName = bt1.text.toString().trim()
        editTextPhone = bt2.text.toString().trim()


        val bt5 = view.findViewById<Button>(R.id.signup1)
        bt5.setOnClickListener {
            editTextEmail = bt3.text.toString().trim()
            editTextPassword = bt4.text.toString().trim()
            if (allField()) {
                firebaseAuth.createUserWithEmailAndPassword(editTextEmail, editTextPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            findNavController().navigate(R.id.action_signupFragment_to_fragment2)


                        } else {

                            Log.w(TAG, "createUserWithEmail:failure", it.exception)
                            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        }
        return view

    }

    private fun allField(): Boolean {
        if (editTextEmail == "") {
            Toast.makeText(context, "email is empty", Toast.LENGTH_SHORT).show()

            return false
        }
        if (editTextPassword.length<=6){
            Toast.makeText(context, "passowrd less than 6 char", Toast.LENGTH_SHORT).show()
            return false
        }


        return true
    }

}






