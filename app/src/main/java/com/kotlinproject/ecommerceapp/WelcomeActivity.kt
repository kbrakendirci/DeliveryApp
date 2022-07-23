package com.kotlinproject.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlinproject.ecommerceapp.databinding.ActivityWelcomeBinding
import com.kotlinproject.ecommerceapp.databinding.FragmentWelcomeBinding
import com.kotlinproject.ecommerceapp.fragment.onboarding.onBoardActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button7.setOnClickListener{
            var intent = Intent(applicationContext, onBoardActivity::class.java)
            startActivity(intent)

        }
    }
}