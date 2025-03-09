package com.example.coffeshopapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.coffeshopapp.ViewModel.MainViewModel
import com.example.coffeshopapp.databinding.ActivityMainBinding
import com.example.coffeshopapp.databinding.ActivitySplashBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel=MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility= View.VISIBLE
        viewModel.loadBanner().observeForever {
            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.Banner)
            binding.progressBarBanner.visibility = View.GONE

            viewModel.loadBanner()
        }
    }
}