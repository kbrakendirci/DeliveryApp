package com.kotlinproject.ecommerceapp.fragment.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlinproject.ecommerceapp.R
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import android.R.string.no
import android.content.Intent
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.kotlinproject.ecommerceapp.MainActivity
import com.kotlinproject.ecommerceapp.databinding.ActivityOnBoardBinding
import com.kotlinproject.ecommerceapp.databinding.FragmentAnasayfaBinding
import kotlinx.android.synthetic.main.activity_on_board.*
import java.time.temporal.TemporalAdjusters.next

class onBoardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnBoardBinding

    companion object {
        const val TOTAL_SLIDE = 4
        var CURRENT_SLIDE = 0
    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityOnBoardBinding.inflate(layoutInflater)
            setContentView(binding.root)
            // Making notification bar transparent
            changeStatusBarColor()
            val actionBar: ActionBar? = supportActionBar
            if (actionBar != null) {
                actionBar.hide();
            }

            val sliderAdapter = SliderAdapter(this)
            binding.viewPager.adapter = sliderAdapter

            addDotIndicator(0)

            val viewPagerPageChangeListener = object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    addDotIndicator(position)
                    CURRENT_SLIDE = position

                    when (position) {
                        0 -> {
                            binding.btnPrev.visibility = View.INVISIBLE
                        }
                        TOTAL_SLIDE - 1 -> {
                            binding.btnNext.text = getString(R.string.finish)
                            binding.btnNext.setOnClickListener {
                                val i = Intent(applicationContext, MainActivity::class.java)
                                startActivity(i)
                                finish()
                            }
                        }
                        else -> {
                            binding.btnPrev.visibility = View.VISIBLE
                            binding.btnNext.text = getString(R.string.next)
                        }
                    }
                }
            }
            binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener)

            binding.btnPrev.setOnClickListener {
                binding.viewPager.currentItem = CURRENT_SLIDE - 1
            }
            binding.btnNext.setOnClickListener {
                if (CURRENT_SLIDE == TOTAL_SLIDE - 1) {
                    finish()
                } else {
                    binding.viewPager.currentItem = CURRENT_SLIDE + 1
                }
            }
        }

    /**
     * Making notification bar transparent
     */
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun addDotIndicator(currentPage: Int) {
        val dots = arrayListOf<TextView>()
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)

        layout_dots.removeAllViews()
        for (i in 0 until TOTAL_SLIDE) {
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#8226;")
            dots[i].textSize = 35f
            dots[i].setTextColor(colorsInactive[currentPage])

            layout_dots.addView(dots[i])
        }

        if (dots.size > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage])
    }

}