package com.kotlinproject.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import com.kotlinproject.ecommerceapp.Activity.onboard.OnboardingActivity
import com.kotlinproject.ecommerceapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            var intent = Intent(applicationContext, OnboardingActivity::class.java)
            startActivity(intent)

        }
        var yukaridanAsagi = AnimationUtils.loadAnimation(this,R.anim.yukaridan_asagi)
        var asagidanyulari = AnimationUtils.loadAnimation(this,R.anim.asagidan_yukari)
        var bulunduguyerdenAsagi = AnimationUtils.loadAnimation(this,R.anim.bulunanyerden_asagi)

        binding.button.animation=yukaridanAsagi
        binding.button.setOnClickListener {


            //Animasyon bitmeden 1sn sonra MainActivity açılsın
            object : CountDownTimer(1000,1000){
                override fun onFinish() {
                    var intent = Intent(applicationContext, OnboardingActivity::class.java)
                    startActivity(intent)
                }

                override fun onTick(p0: Long) {
                    //işimiz yok burasıyla
                }

            }.start()

        }
    }
}