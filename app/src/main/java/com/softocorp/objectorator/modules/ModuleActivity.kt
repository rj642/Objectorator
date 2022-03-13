package com.softocorp.objectorator.modules

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.softocorp.objectorator.R
import com.softocorp.objectorator.ViewPagerDAdapter
import com.softocorp.objectorator.databinding.ActivityModuleBinding
import com.softocorp.objectorator.modules.ui.FacialFragment
import com.softocorp.objectorator.modules.ui.FirearmFragment
import com.softocorp.objectorator.modules.ui.HeightFragment


class ModuleActivity : AppCompatActivity() {

    private var _binding: ActivityModuleBinding? = null

    private val binding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityModuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar()

        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .detectUntaggedSockets()
                .detectAll()
                .penaltyLog() //                    .penaltyDeath()
                .build()
        )

        val list = arrayListOf<Fragment>(
            FacialFragment(),
            FirearmFragment(),
            HeightFragment()
        )

        val adapter = ViewPagerDAdapter(list, supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayoutModules, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.setText("Facial Recognition")
                }
                1 -> {
                    tab.setText("Firearm Recognition")
                }
                2 -> {
                    tab.setText("Height Detector")
                }
            }
        }.attach()

        chooseImage(binding.root)

    }

    @SuppressLint("NewApi")
    fun chooseImage(view: View) {
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        } else {
            binding.fabAdd.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 101)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.fabAdd.setOnClickListener {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, 101)
                }
            }
        }
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