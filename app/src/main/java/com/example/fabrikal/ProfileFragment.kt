package com.example.fabrikal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fabrikal.model.CreditCard
import com.example.fabrikal.model.UserAddress
import com.example.fabrikal.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*


// TODO: Rename parameter arguments, choose names that match
private const val USER_PARAM = "USUARIO"

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userData : UserProfile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        userData = arguments?.getParcelable<UserProfile>(USER_PARAM) ?: return


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nombreUsuarioTextView.text = "${userData.nombres} ${userData.apellidos}"
        emailTextView.text = userData.email


        button_direccion.setOnClickListener {
            val intent = Intent(activity,AdressActivity::class.java)
            val userAddress = UserAddress(userData.telefono,
            userData.direccion,
            userData.tipoDireccion,
            userData.lote,
            userData.provincia,
            userData.urbanizacion,
            userData.distrito,
            userData.latitud,
            userData.longitud)
            intent.putExtra("DIRECCION", userAddress)
            startActivityForResult(intent,AdressActivity.REQUEST_CODE)
        }

        button_tarjeta.setOnClickListener {
            val intent = Intent(activity,CreditCardActivity::class.java)
            val creditCard = CreditCard(userData.nombreTitularTarjeta,
                    userData.vigenciaMes,
                    userData.vigenciaAnio,
                    userData.nroTarjeta)
            intent.putExtra(CreditCardActivity.PARAM_CARD,creditCard)
            startActivityForResult(intent,CreditCardActivity.REQUEST_CODE)
        }

        button_llamanos.setOnClickListener {
            val phone = "+34666777888"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }
        button_cerrar_sesion.setOnClickListener {
            auth.signOut()
            val intent = Intent(activity,MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(userProfile : UserProfile?) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_PARAM, userProfile)
                }
            }
    }
}