package com.example.fabrikal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_go.*

class GoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go)
        go.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        textView6.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}