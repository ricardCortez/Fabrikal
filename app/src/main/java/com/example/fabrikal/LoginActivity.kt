package com.example.fabrikal

import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonIngresar.setOnClickListener{
            if (inputEmailAddress.text.isEmpty()||inputPassword.text.isEmpty()){
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }
        }
        buttonCrearCuenta.setOnClickListener{
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
        imageBack.setOnClickListener{
            finish()
        }
    }
}