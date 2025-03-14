package com.example.coffeshopapp.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeshopapp.Adapter.CategoryAdapter
import com.example.coffeshopapp.Adapter.PopularAdapter
import com.example.coffeshopapp.ViewModel.MainViewModel
import com.example.coffeshopapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategory()
        initPopular()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.loadBanner().observeForever {
            Glide.with(this@MainActivity).load(it[0].url).into(binding.Banner)
            binding.progressBarBanner.visibility = View.GONE

            viewModel.loadBanner()
        }
    }


    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.loadCategory().observeForever {
            binding.recyclerViewCategory.layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL, false
            )

            binding.recyclerViewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        }
        viewModel.loadCategory()
    }

    private fun initPopular() {
        binding.progressBar2.visibility = View.VISIBLE
        viewModel.loadPopular().observeForever {
            binding.recyclerViewPopularCoffe.layoutManager = GridLayoutManager(this, 2)
            binding.recyclerViewPopularCoffe.adapter = PopularAdapter(it)
            binding.progressBar2.visibility = View.GONE
        }
        viewModel.loadPopular()
    }
}