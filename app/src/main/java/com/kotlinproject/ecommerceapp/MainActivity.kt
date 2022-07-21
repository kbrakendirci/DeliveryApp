package com.kotlinproject.ecommerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kotlinproject.ecommerceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var tasarim : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)


        setContentView(tasarim.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController( tasarim.bottomNav, navHostFragment.navController)

        var size : Int = 0

        badgeNumber(size)

    }

    fun badgeNumber(size : Int?) {
        val badge = tasarim.bottomNav.getOrCreateBadge(R.id.sepetFragment)
        if (size != null) {
            badge.number = size
        }
    }
}