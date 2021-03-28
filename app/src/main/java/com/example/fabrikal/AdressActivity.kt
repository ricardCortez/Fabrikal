package com.example.fabrikal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_adress.*

class AdressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adress)
        imageView7.setOnClickListener{
            finish()
        }
        
        button5.setOnClickListener{
            val intent = Intent(this,GpsActivity::class.java)
            startActivity(intent)
        }
    }
}