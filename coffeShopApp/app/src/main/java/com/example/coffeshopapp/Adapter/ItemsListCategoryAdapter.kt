package com.example.coffeshopapp.Adapter

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshopapp.Activity.DetailActivity
import com.example.coffeshopapp.Domain.itemsModel
import com.example.coffeshopapp.databinding.ViewholderItemPicLeftBinding
import com.example.coffeshopapp.databinding.ViewholderItemPicRightBinding

class ItemsListCategoryAdapter(val items:MutableList<itemsModel>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        companion object{
            const val TYPE_ITEM1=0
            const val TYPE_ITEM2=1
        }
    lateinit var context: Context
    override fun getItemViewType(position: Int): Int {
        return if (position%2 ==0) TYPE_ITEM1 else TYPE_ITEM2
    }

    class ViewholderItem1(val binding: ViewholderItemPicRightBinding):
    RecyclerView.ViewHolder(binding.root)

    class ViewholderItem2(val binding: ViewholderItemPicLeftBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context=parent.context
        return when (viewType){
            TYPE_ITEM1 -> {
                val binding=ViewholderItemPicRightBinding.inflate(
                    LayoutInflater.from(context),
                    parent,false
                )
                ViewholderItem1(binding)
            }
            TYPE_ITEM2 -> {
                val binding=ViewholderItemPicLeftBinding.inflate(
                    LayoutInflater.from(context),
                    parent,false
                )
                ViewholderItem2(binding)
            }
            else -> throw IllegalArgumentException("Invalidview type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item=items[position]
        fun bindCommonData(
            titleTxt:String,
            priceTxt:String,
            rating:Float,
            picUrl:String
        ){
            when(holder){
                is ViewholderItem1 ->{
                    holder.binding.titleTxt.text=titleTxt
                    holder.binding.priceTxt.text=priceTxt
                    holder.binding.ratingBar.rating=rating

                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)

                    holder.itemView.setOnClickListener {
                        val intent=Intent(context,DetailActivity::class.java)
                        intent.putExtra("object",items[position])
                        context.startActivity(intent)
                    }
                }

                is ViewholderItem2 ->{
                    holder.binding.titleTxt.text=titleTxt
                    holder.binding.priceTxt.text=priceTxt
                    holder.binding.ratingBar.rating=rating

                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)

                    holder.itemView.setOnClickListener {
                        val intent=Intent(context,DetailActivity::class.java)
                        intent.putExtra("object",items[position])
                        context.startActivity(intent)
                    }
                }
            }

        }
        bindCommonData(
            item.title,
            "${item.price} USD",
            item.rating.toFloat(),
            item.picUrl[0]
        )
    }
}