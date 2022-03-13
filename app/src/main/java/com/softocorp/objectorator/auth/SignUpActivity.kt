package com.softocorp.objectorator.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.softocorp.objectorator.databinding.ActivitySignUpBinding
import com.softocorp.objectorator.modules.ModuleActivity

class SignUpActivity : AppCompatActivity() {

    private var _binding: ActivitySignUpBinding? = null

    private val mAuth = FirebaseAuth.getInstance()

    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        binding.apply {
            signupButton.setOnClickListener {
                signupButton.text = ""
                progressBar.visibility = View.GONE
                if (textEmail.text.toString().trim()
                        .isEmpty() || textObjectoratorPassword.text.toString().trim()
                        .isEmpty() || textConfirmPassword.text.toString().trim().isEmpty()
                ) {

                } else {
                    mAuth.createUserWithEmailAndPassword(
                        textEmail.text.toString().trim(),
                        textObjectoratorPassword.text.toString().trim()
                    )
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                // send to module activity
                                signupButton.text = "Sign Up"
                                progressBar.visibility = View.GONE
                                val intent = Intent(this@SignUpActivity, ModuleActivity::class.java)
                                startActivity(intent)
                            } else {
                                authFailed.visibility = View.VISIBLE
                                desc.text = "Some Error Occurred!"
                                signupButton.text = "Sign Up"
                                progressBar.visibility = View.GONE
                                Handler().postDelayed({
                                    authFailed.visibility = View.GONE
                                }, 2000)
                            }
                        }
                }
            }
        }
        setContentView(binding.root)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}