package com.softocorp.objectorator.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.softocorp.objectorator.R
import com.softocorp.objectorator.databinding.ActivityLoginBinding
import com.softocorp.objectorator.modules.ModuleActivity

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null

    private val binding
    get() = _binding!!

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.apply {
            loginButton.setOnClickListener {
                if (textObjectoratorId.text.toString().trim().isEmpty() || textObjectoratorPassword.text.toString().trim().isEmpty()) {
                    authFailed.visibility = View.VISIBLE
                } else {
                    loginButton.setText("")
                    progressBar.visibility = View.VISIBLE
                    authFailed.visibility = View.GONE
                    mAuth.signInWithEmailAndPassword(textObjectoratorId.text.toString().trim(), textObjectoratorPassword.text.toString().trim())
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                // we'll perform later
                                val intent = Intent(this@LoginActivity, ModuleActivity::class.java)
                                startActivity(intent)
                                loginButton.setText("Login")
                                progressBar.visibility = View.GONE
                            } else {
                                authFailed.visibility = View.VISIBLE
                                loginButton.setText("Retry")
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