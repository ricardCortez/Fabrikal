package com.example.fabrikal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_credit_card.*

class CreditCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_card)
        imageView13.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}