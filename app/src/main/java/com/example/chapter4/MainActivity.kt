package com.example.chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navControler = findNavController(R.id.fragmentContainerView)

        binding.bottomNav.setupWithNavController(navControler)

        val botNavigation = binding.bottomNav
        val navHostNavigation = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navControl = navHostNavigation.navController


        navControl.addOnDestinationChangedListener { _, destinat, _ ->
            when (destinat.id) {
                R.id.homeFargment -> {
                    botNavigation.visibility = View.VISIBLE
                }
                R.id.keranjang -> {
                    botNavigation.visibility = View.VISIBLE
                }
                R.id.profile -> {
                    botNavigation.visibility = View.VISIBLE
                }
                else -> {
                    botNavigation.visibility = View.VISIBLE
                }
            }
        }
    }
}
