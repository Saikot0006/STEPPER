package com.example.mystepper

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.MeasureSpec
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.mystepper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listData: MutableList<String> = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
       // setContentView(R.layout.activity_main)
        listData.add("First step")
        listData.add("Second step")
        listData.add("Third step")

        Log.e("size", "onCreate: "+listData.size )
        setupStepView()
        stepViewPager()
        setupButton()




    }

    fun setupStepView(){
        binding.stepView.state
            .steps(listOf("First step","Second step","Third step"))
            .animationDuration(resources.getInteger(android.R.integer.config_shortAnimTime))
            .stepsNumber(3)
            .commit()

    }

    fun  stepViewPager(){
        binding.viewpager2.adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        binding.viewpager2.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setButtons(position)
                }
            }
        )
    }

    fun setupButton(){
        binding.NextButton.setOnClickListener {
            binding.viewpager2.setCurrentItem(binding.viewpager2.currentItem+1,false)
        }

        binding.backButton.setOnClickListener {
            binding.viewpager2.setCurrentItem(binding.viewpager2.currentItem-1,false)
        }
    }

    fun setButtons(position : Int){
        when(position){
            0 -> {
                binding.backButton.visibility = View.INVISIBLE
                binding.NextButton.visibility = View.VISIBLE
            }
            1 -> {
                binding.backButton.visibility = View.VISIBLE
                binding.NextButton.visibility = View.VISIBLE
            }
            2 -> {
                binding.backButton.visibility = View.VISIBLE
                binding.NextButton.visibility = View.INVISIBLE
            }
        }
    }



}