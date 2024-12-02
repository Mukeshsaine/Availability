package com.example.availability.Activity

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.availability.Fragment.AvailabilityFragment
import com.example.availability.BookingsFragment
import com.example.availability.EnquiriesFragment
import com.example.availability.ProfileFragment
import com.example.availability.R
import com.example.availability.ServicesFragment
import com.example.availability.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val HomeFragment = BookingsFragment()
        val ListFragment = EnquiriesFragment()
        val StopFragment = AvailabilityFragment()
        val RecipesFragment = ServicesFragment()
        val CartFragment = ProfileFragment()

        setCurrentFragment(RecipesFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Bookings ->setCurrentFragment(RecipesFragment)
                R.id.Enquiries ->setCurrentFragment(ListFragment)
                R.id.Availability ->setCurrentFragment(StopFragment)
                R.id.Services ->setCurrentFragment(HomeFragment)
                R.id.Profile ->setCurrentFragment(CartFragment)

            }
            true
        }
        // window.statusBarColor = ContextCompat.getColor(this, R.color.gradient_background)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    ContextCompat.getColor(this, R.color.green),
                    ContextCompat.getColor(this, R.color.yellow1),
                    ContextCompat.getColor(this, R.color.yellow2)
                )
            )
            window.statusBarColor = Color.TRANSPARENT
            window.setBackgroundDrawable(gradientDrawable)
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}