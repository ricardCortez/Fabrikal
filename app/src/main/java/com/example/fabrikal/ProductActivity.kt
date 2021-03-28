package com.example.fabrikal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        button12.setOnClickListener{
            val intent = Intent(this,ConfirmShopActivity::class.java)
            startActivity(intent)
        }
        imageView15.setOnClickListener{
            finish()
        }
    }
}