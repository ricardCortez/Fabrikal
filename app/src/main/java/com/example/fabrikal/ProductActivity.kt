package com.example.fabrikal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fabrikal.model.OrdenCompra
import com.example.fabrikal.model.ShoeHomeItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {

    var ordenCompra : OrdenCompra? = null
    var itemProducto : ShoeHomeItem? = null
    private lateinit var  sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        itemProducto = intent.getParcelableExtra<ShoeHomeItem>("PRODUCTO")
        itemProducto?.also { item ->
            Picasso.get().load(item.imagen).into(imagenImageView)
            marcaTextView.text = item.marca
            modeloTextView.text = item.modelo
            precioTextView.text = "S/. ${item.precio}"
            colorTextView.text = item.color
        }

        sharedPreferences = getSharedPreferences("FABRIKAL", Context.MODE_PRIVATE)
        ordenCompra = OrdenCompra()

        sharedPreferences.getString("ORDEN_COMPRA","")?.let { orden ->
            if(orden.isNotEmpty()){
                ordenCompra = Gson().fromJson(orden,OrdenCompra::class.java)
            }
        }


        imag_atras_product.setOnClickListener {
           finish()
        }
        button_comprar.setOnClickListener {
            itemProducto?: return@setOnClickListener

            updateProductoOrden(itemProducto!!)
            val intent = Intent(this,FinCompraActivity::class.java)
            startActivityForResult(intent,FinCompraActivity.REQUEST_CODE)
        }
    }


    fun updateProductoOrden(shoeItem : ShoeHomeItem){
        ordenCompra?.productoID =  shoeItem.productoID
        ordenCompra?.modelo = shoeItem.modelo
        ordenCompra?.precio = shoeItem.precio
        sharedPreferences.edit().putString("ORDEN_COMPRA", Gson().toJson(ordenCompra)).apply()
        sharedPreferences.edit().putString("PRODUCTO_COMPRA",Gson().toJson(shoeItem)).apply()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == FinCompraActivity.REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                finish()
            }
        }
    }
}