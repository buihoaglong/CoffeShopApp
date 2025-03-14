package com.example.coffeshopapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeshopapp.Domain.BannerModel
import com.example.coffeshopapp.Domain.CategoryModel
import com.example.coffeshopapp.Domain.itemsModel
import com.example.coffeshopapp.Repository.MainRepository

class MainViewModel:ViewModel() {
    private val repository=MainRepository()

    fun loadBanner():LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory():LiveData<MutableList<CategoryModel>>{
     return repository.loadCategory()
    }

    fun loadPopular():LiveData<MutableList<itemsModel>>{
        return repository.loadPopular()
    }

    fun loadItems(categoryId:String):LiveData<MutableList<itemsModel>>{
        return repository.loadItemCategory(categoryId)
    }
}