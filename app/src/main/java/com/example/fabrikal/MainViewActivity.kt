package com.example.fabrikal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_view.*

class MainViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)
        imageView19.setOnClickListener{
            val intent = Intent(this,ProductActivity::class.java)
            startActivity(intent)
        }
    }
}