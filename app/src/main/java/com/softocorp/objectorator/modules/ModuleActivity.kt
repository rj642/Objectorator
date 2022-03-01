package com.softocorp.objectorator.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softocorp.objectorator.R
import com.softocorp.objectorator.databinding.ActivityModuleBinding

class ModuleActivity : AppCompatActivity() {

    private var _binding: ActivityModuleBinding? = null

    private val binding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityModuleBinding.inflate(layoutInflater)

        setUpToolbar()

        setContentView(binding.root)
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_button)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}