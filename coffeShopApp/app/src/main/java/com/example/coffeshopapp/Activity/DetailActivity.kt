package com.example.coffeshopapp.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.coffeshopapp.Domain.itemsModel
import com.example.coffeshopapp.R
import com.example.coffeshopapp.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: itemsModel
    private lateinit var managementCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        bundle()
        initSizeList()

    }

    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }

            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                largeBtn.setBackgroundResource(0)
            }

            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
            }
        }
    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as itemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "$" + item.price
            ratingTxt.text = item.rating.toString()

            // Đặt số lượng ban đầu
            numberitemTxt.text = item.numberInCart.toString()

            // Xử lý nút tăng số lượng
            plusCart.setOnClickListener {
                item.numberInCart++
                numberitemTxt.text = item.numberInCart.toString()
            }

            // Xử lý nút giảm số lượng
            minusBtn.setOnClickListener {
                if (item.numberInCart > 1) { // Đảm bảo số lượng không giảm xuống dưới 1
                    item.numberInCart--
                    numberitemTxt.text = item.numberInCart.toString()
                }
            }

            // Xử lý nút "Add to Cart"
            addToCartBtn.setOnClickListener {
                item.numberInCart = Integer.valueOf(numberitemTxt.text.toString())
                managementCart.insertItems(item)
            }

            // Xử lý nút quay lại
            backBtn.setOnClickListener {
                finish()
//                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            }
        }
    }
}