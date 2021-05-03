package com.example.fabrikal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import androidx.fragment.app.Fragment
import com.example.fabrikal.model.OrdenCompra
import com.example.fabrikal.model.ShoeHomeItem
import com.example.fabrikal.model.UserProfile
import com.google.gson.Gson
import com.google.gson.TypeAdapter

class HomeActivity : AppCompatActivity() {

    var ordenCompra : OrdenCompra? = null
    var productoOrdenCompra : ShoeHomeItem? = null
    var userData : UserProfile? = null
    private lateinit var  sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPreferences = getSharedPreferences("FABRIKAL", Context.MODE_PRIVATE)
        ordenCompra = OrdenCompra()
        loadSavedInformation()
        showHome()

        homeButton.setOnClickListener {
            showHome()
        }
        searchButton.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
        shoppingButton.setOnClickListener {
            if(!ordenCompra?.modelo.isNullOrEmpty()) {
                val intent = Intent(this, ProductActivity::class.java)
                intent.putExtra("PRODUCTO",productoOrdenCompra)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Carrito Vacio",Toast.LENGTH_LONG).show()
            }
        }
        profileButton.setOnClickListener {
                showProfile()
        }
    }

    fun loadSavedInformation(){
        sharedPreferences.getString("ORDEN_COMPRA","")?.let { orden ->
            if(orden.isNotEmpty()){
                ordenCompra = Gson().fromJson(orden,OrdenCompra::class.java)
            }else{
                ordenCompra = OrdenCompra()
            }
        }

        sharedPreferences.getString("PRODUCTO_COMPRA","")?.let {
                productoCompra ->
            if(productoCompra.isNotEmpty()){
                productoOrdenCompra = Gson().fromJson(productoCompra,ShoeHomeItem::class.java)
            } else{
                productoOrdenCompra = null
            }
        }

        intent.getParcelableExtra<UserProfile>("USER_PROFILE")?.let { userProfile ->
            userData = userProfile
            sharedPreferences.edit().putString("USER_COMPRA",Gson().toJson(userProfile)).apply()
        }?: kotlin.run {
            sharedPreferences.getString("USER_COMPRA", "")?.let { perfilString ->
                if(perfilString.isNotEmpty()){
                    userData = Gson().fromJson(perfilString,UserProfile::class.java)
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadSavedInformation()
    }


    private fun showProfile(){
        val fragment = ProfileFragment.newInstance(userData)
        supportFragmentManager.beginTransaction().replace(R.id.contentLayout,fragment,"profile").commit()
    }

    private fun showHome(){
        val fragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.contentLayout,fragment,"home").commit()
    }
}