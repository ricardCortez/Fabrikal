package com.example.fabrikal

import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import androidx.fragment.app.Fragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        showHome()

        homeButton.setOnClickListener {
            showHome()
        }
        searchButton.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
        shoppingButton.setOnClickListener {
            val intent = Intent(this,ProductActivity::class.java)
            startActivity(intent)
        }
        profileButton.setOnClickListener {
                showProfile()
        }
    }


    private fun showProfile(){
        val fragment = ProfileFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.contentLayout,fragment,"profile").commit()
    }

    private fun showHome(){
        val fragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.contentLayout,fragment,"home").commit()
    }
}