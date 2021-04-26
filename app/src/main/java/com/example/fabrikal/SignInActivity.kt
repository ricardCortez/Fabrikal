package com.example.fabrikal

import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        buttonCrear.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
        imageBack1.setOnClickListener{
            finish()
        }
        buttonAddCreditCard.setOnClickListener {
            val intent = Intent( this, CreditCardActivity::class.java)
            startActivity(intent)
        }
        buttonAddAdrres.setOnClickListener {
            val intent = Intent(this, AdressActivity::class.java)
            startActivity(intent)
        }
    }
}