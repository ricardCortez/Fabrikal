package com.example.fabrikal

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        entrar.setOnClickListener{
            val intent = Intent(this,GoActivity::class.java)
            startActivity(intent)
        }
    }
}