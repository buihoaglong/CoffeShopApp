package com.example.coffeshopapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeshopapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}