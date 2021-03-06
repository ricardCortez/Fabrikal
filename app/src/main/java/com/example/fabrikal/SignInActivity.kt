package com.example.fabrikal

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fabrikal.model.CreditCard
import com.example.fabrikal.model.UserAddress
import com.example.fabrikal.model.UserProfile
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_in.*
import permissions.dispatcher.*
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.random.Random

@RuntimePermissions
class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private var creditCard : CreditCard? = null
    private var address : UserAddress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = FirebaseAuth.getInstance()
        dbReference = FirebaseDatabase.getInstance().getReference("Users")
        buttonCrear.setOnClickListener{
            if (checkBox.isChecked){
                createUser()
            }else{
                Toast.makeText(this, "Acepta los terminos del servicio", Toast.LENGTH_SHORT).show()
            }
        }
        imageBack1.setOnClickListener{
            finish()
        }
        buttonAddCreditCard.setOnClickListener {
            val intent = Intent( this, CreditCardActivity::class.java)
            startActivityForResult(intent,CreditCardActivity.REQUEST_CODE)
        }
        buttonAddAdrres.setOnClickListener {
            val intent = Intent(this, AdressActivity::class.java)
            startActivityForResult(intent,AdressActivity.REQUEST_CODE)
        }
        buttonC.setOnClickListener {
            abrirCamaraWithPermissionCheck()
        }
    }
    private fun convertBitmapToBase64(bitmap: Bitmap) : String{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream)
        val imge = stream.toByteArray()
        return Base64.encodeToString(imge,Base64.DEFAULT)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    private var fotoString : String? = null
    fun createUser(){
        //TODO: Validar input
        val userEmail = inputCreateEmailAdress.text.toString()
        val userPassword = inputCreatePassword.text.toString()

        if(address == null){
            Toast.makeText(this, "INGRESAR DIRECCION",Toast.LENGTH_LONG).show()
            return
        }else if (creditCard == null){
            Toast.makeText(this,"INGRESAR TARJETA", Toast.LENGTH_LONG).show()
            return
        }

        val userInfo = UserProfile("",
                fotoString,//sin foto
            inputCreateName.text.toString(),
            inputCreateLastName.text.toString(),
            userEmail,
            inputCreateDNI.text.toString(),
                address?.telefono,
                address?.distrito,
                address?.provincia,
                address?.urbanizacion,
                address?.nroLote,
                address?.tipoDireccion,
                address?.direccion,
                address?.latitud,
                address?.longitud,
                creditCard?.nroTarjeta,
                creditCard?.vigenciaMes,
                creditCard?.vigenciaAnio,
                creditCard?.nombreTitular
        )

        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, OnCompleteListener{ task ->
            if(task.isSuccessful){
                task.result?.let { authResult ->
                    val userID = authResult.user?.uid ?: ""
                    userInfo.userId = userID
                    dbReference.child(userID).setValue(userInfo).addOnCompleteListener {
                        goHome()
                    }
                }

            }else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
            }

        })


    }

    fun goHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun abrirCamara() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(
                takePictureIntent,
                CamaraActivity.REQUEST_IMAGE_CAPTURE
            )
        }
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRationaleForCamera(request: PermissionRequest) {
        showRationaleDialog("Queremos tomar fotos", request)
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onCameraDenied() {
        Toast.makeText(this, "permiso denegado", Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onCameraNeverAskAgain() {
        Toast.makeText(this, "nunca preguntar", Toast.LENGTH_SHORT).show()
    }

    private fun showRationaleDialog(
        texto: String,
        request: PermissionRequest
    ) {
        AlertDialog.Builder(this)
            .setPositiveButton("Permitir") { _, _ -> request.proceed() }
            .setNegativeButton("Denegar") { _, _ -> request.cancel() }
            .setCancelable(false)
            .setMessage(texto)
            .show()
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CamaraActivity.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            fotoString = convertBitmapToBase64(imageBitmap)
            imageCarmaraNew.setImageBitmap(imageBitmap)
        }else if(requestCode == CreditCardActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            creditCard = data?.extras?.getParcelable<CreditCard>("CREDITCARD")
        }else if(requestCode == AdressActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            address = data?.extras?.getParcelable<UserAddress>("DIRECCION")
        }
    }

}