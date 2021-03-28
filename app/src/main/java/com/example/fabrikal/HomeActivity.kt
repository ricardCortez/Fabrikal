package com.example.fabrikal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        /*button3.setOnClickListener{
            val intent = Intent(this,AdressActivity::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener{
            val intent = Intent(this,CreditCardActivity::class.java)
            startActivity(intent)
        }
        imageView8.setOnClickListener{
            val intent = Intent(this, MainViewActivity::class.java)
            startActivity(intent)
        }
        button14.setOnClickListener{
            val intent = Intent(this,GoActivity::class.java)
            startActivity(intent)
        }
        imageView12.setOnClickListener{
            finish()
        }*/
    }
}