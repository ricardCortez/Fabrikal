package com.example.fabrikal

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fabrikal.model.UserProfile
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        dbReference = FirebaseDatabase.getInstance().getReference("Users")

        buttonIngresar.setOnClickListener{

            val emailLogin = inputEmailAddress.text.trim().toString()
            val passwordLogin = inputPassword.text.trim().toString()

            if (emailLogin.isEmpty()||passwordLogin.isEmpty()){
                showAlertnull()
            }else{
                 val hud = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setMaxProgress(100)
                    .setAnimationSpeed(2)
                    .show()
                hud.setAutoDismiss(true)


                auth.signInWithEmailAndPassword(emailLogin, passwordLogin).addOnCompleteListener(
                    this,
                    OnCompleteListener { task ->
                        if (task.isSuccessful) {

                            task.result?.let { authResult ->

                                dbReference.child(authResult.user.uid)
                                    .addValueEventListener(object :
                                        ValueEventListener {
                                        override fun onCancelled(error: DatabaseError) {

                                        }

                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val userInfo =
                                                snapshot.getValue(UserProfile::class.java) ?: return
                                            goHome(userInfo)

                                        }

                                    })

                            }


                        } else {
                            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })

            }
        }

        buttonCrearCuenta.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        imageBack.setOnClickListener{
            finish()
        }
    }


    private fun goHome(userInfo: UserProfile){
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("USER_PROFILE", userInfo)
        startActivity(intent)
        finish()
    }

    private fun showAlertnull() {
       val alertDialog =  AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Ingrese usuario y/o password")
            .setPositiveButton(
                "Aceptar",
                null
            ).create()

        alertDialog.show()
    }

    private fun showAlertRegistrado() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("El usuario ya se encuentra resgistrado")
            .setPositiveButton(
                "Aceptar",
                null
            ).create()

        alertDialog.show()
    }

    private fun showAlertUsuarioNR() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("El usuario no se encuentra resgistrado")
            .setPositiveButton(
                "Aceptar",
                null
            ).create()
        alertDialog.show()
    }

}



