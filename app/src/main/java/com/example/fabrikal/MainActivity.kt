package com.example.fabrikal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        entrar.setOnClickListener{
            if(auth.currentUser == null) {
                val intent = Intent(this, GoActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
    }
}