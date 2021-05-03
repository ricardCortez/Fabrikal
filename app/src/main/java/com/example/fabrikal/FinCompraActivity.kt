package com.example.fabrikal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fabrikal.common.Utils
import com.example.fabrikal.model.OrdenCompra
import com.example.fabrikal.model.UserProfile
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_fin_compra.*
import kotlinx.android.synthetic.main.activity_product.*

class FinCompraActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE = 1001
    }
    private lateinit var  sharedPreferences : SharedPreferences
    private lateinit var ordenCompra: OrdenCompra
    private lateinit var dbReference: DatabaseReference
    private lateinit var userProfile: UserProfile


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fin_compra)

        dbReference = FirebaseDatabase.getInstance().getReference("Orders")
        sharedPreferences =  getSharedPreferences("FABRIKAL", Context.MODE_PRIVATE)
        sharedPreferences.getString("ORDEN_COMPRA","")?.let { orden ->
            if(orden.isNotEmpty()){
                ordenCompra = Gson().fromJson(orden, OrdenCompra::class.java)
            }
        }

        sharedPreferences.getString("USER_COMPRA","")?.let{ userCompra ->
            if(userCompra.isNotEmpty()){
                userProfile = Gson().fromJson(userCompra, UserProfile::class.java)
            }

        }

        ordenCompra.nombre = "${userProfile.nombres} ${userProfile.apellidos}"
        ordenCompra.telefono = userProfile.telefono

        envioDomicilioButton.setOnClickListener {
            ordenCompra.direccion = userProfile.direccion
            ordenCompra.tipoEnvio = "DOMICILIO"
            ordenCompra.latDomicilio = userProfile.latitud
            ordenCompra.longDomicilio = userProfile.longitud
        }

        tiendaButton.setOnClickListener {
            ordenCompra.direccion =  null
            ordenCompra.tipoEnvio = "TIENDA"
        }

        tarjetaButton.setOnClickListener {
            ordenCompra.metodoPago = "TARJETA"
            ordenCompra.nroTarjeta = userProfile.nroTarjeta
        }

        efectivoButton.setOnClickListener {
            ordenCompra.metodoPago = "EFECTIVO"
        }

        comprarButton.setOnClickListener {
            //VALIDAR PAGO

            if(ordenCompra.tipoEnvio.isNullOrEmpty() || ordenCompra.metodoPago.isNullOrEmpty()){
                Toast.makeText(this,"Escoger metodo y envio",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            //GUARDAR COMPRA
            ordenCompra.ordenID = Utils.generateRandomHash()
            dbReference.child(ordenCompra.ordenID!!).setValue(ordenCompra)

            Toast.makeText(this,"FELICIDADES!! COMPRA REALIZADA",Toast.LENGTH_LONG).show()
            sharedPreferences.edit().remove("ORDEN_COMPRA").apply()
            sharedPreferences.edit().remove("PRODUCTO_COMPRA").apply()
            setResult(Activity.RESULT_OK)
            finish()

        }

        imag_fin_compra.setOnClickListener {
            finish()
        }
    }
}