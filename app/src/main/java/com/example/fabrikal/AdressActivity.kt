package com.example.fabrikal

import android.app.Activity
import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fabrikal.model.UserAddress
import kotlinx.android.synthetic.main.activity_adress.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class AdressActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE = 1003
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adress)
        closeButton.setOnClickListener{
            finish()
        }

        buttonUbicarDireccion.setOnClickListener{
            val intent = Intent(this,GpsActivity::class.java)
            startActivity(intent)
        }

        guardarButton.setOnClickListener {
            guardarDireccion()
        }
    }


    fun guardarDireccion(){
        val telefono = inputCreatePhoneNumber.text.toString()
        val tipoDireccion = inputCreateTipoDireccion.text.toString()
        val direccion = inputCreateDireccion.text.toString()
        val lote = inputCreateLote.text.toString()
        val provincia = inputCreateProvincia.text.toString()
        val urbanizacion = inputCreateUrbanizacion.text.toString()
        val distrito = inputCreateDistrito.text.toString()
        val latitud = ""
        val longitud = ""

        val direccionUsuario = UserAddress(telefono,
                direccion,
                tipoDireccion,
                lote,
                provincia,
                urbanizacion,
                distrito,
                latitud,
                longitud
        )


        val direccionBundle = Bundle().apply {
            putParcelable("DIRECCION",direccionUsuario)
        }
        intent.putExtras(direccionBundle)
        setResult(Activity.RESULT_OK,intent)
        Toast.makeText(this,"DIRECCION ACTUALIZADA", Toast.LENGTH_LONG).show()
        finish()

    }
}