package com.example.fabrikal

import android.app.Activity
import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fabrikal.model.CreditCard
import kotlinx.android.synthetic.main.activity_credit_card.*

class CreditCardActivity : AppCompatActivity() {
    companion object{
        const val PARAM_CARD = "CREDITCARD"
        const val REQUEST_CODE = 1002
    }

    private var editCard : CreditCard? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_card)

        intent.getParcelableExtra<CreditCard>(PARAM_CARD)?.let { card ->
            editCard = card
            titularEditText.setText(editCard?.nombreTitular)
            vigenciaEditText.setText("${editCard?.vigenciaMes}/${editCard?.vigenciaAnio}")
            nroTarjetaEditText.setText(editCard?.nroTarjeta)
        }

        if(editCard != null){
            guardarButton.visibility = View.GONE
        }

        closeButton.setOnClickListener{
            finish()
        }

        guardarButton.setOnClickListener {
            guardarTarjeta()
        }
    }

    fun guardarTarjeta(){
        val titular =   titularEditText.text.toString()
        val vigencia = vigenciaEditText.text.toString()
        val nroTarjeta = nroTarjetaEditText.text.toString()

        var vigenciaCampos = 0
        var vigenciaMes = ""
        var vigenciaAno = ""
        vigencia.split("/").forEach {
            if(vigenciaCampos == 0){
                vigenciaMes = it
            }else{
                vigenciaAno = it
            }
            vigenciaCampos++
        }
        val creditCard = CreditCard(titular,vigenciaMes,vigenciaAno,nroTarjeta)

        val cardBundle = Bundle().apply {
            putParcelable(PARAM_CARD,creditCard)
        }
        intent.putExtras(cardBundle)
        setResult(Activity.RESULT_OK,intent)
        Toast.makeText(this,"TARJETA ACTUALIZADA",Toast.LENGTH_LONG).show()
        finish()

    }

}