package com.example.coffeshopapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeshopapp.Domain.BannerModel
import com.example.coffeshopapp.Repository.MainRepository

class MainViewModel:ViewModel() {
    private val repository=MainRepository()

    fun loadBanner():LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }
}