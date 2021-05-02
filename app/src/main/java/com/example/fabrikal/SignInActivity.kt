package com.example.fabrikal

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fabrikal.model.UserProfile
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_in.*
import permissions.dispatcher.*

@RuntimePermissions
class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference

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
            startActivity(intent)
        }
        buttonAddAdrres.setOnClickListener {
            val intent = Intent(this, AdressActivity::class.java)
            startActivity(intent)
        }
        buttonC.setOnClickListener {
            abrirCamaraWithPermissionCheck()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    fun createUser(){
        //TODO: Validar input
        val userEmail = inputCreateEmailAdress.text.toString()
        val userPassword = inputCreatePassword.text.toString()
        val userInfo = UserProfile("",
            inputCreateName.text.toString(),
            inputCreateLastName.text.toString(),
            userEmail,
            inputCreateDNI.text.toString())


        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, OnCompleteListener{ task ->
            if(task.isSuccessful){
                task.result?.let { authResult ->
                    val userID = authResult.user.uid ?: ""
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
            imageCarmaraNew.setImageBitmap(imageBitmap)
        }
    }

}