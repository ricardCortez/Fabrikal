package com.example.fabrikal

import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_adress.*
import kotlinx.android.synthetic.main.activity_credit_card.*

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
        imageView7.setOnClickListener{
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
    }
}