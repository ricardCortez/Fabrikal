package com.example.fabrikal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProfile(
    var userId : String? = "",
    var nombres : String? = "",
    var apellidos : String? = "",
    var email : String? = "",
    var numeroDNI : String? = "",
    var telefono : String? = "",
    var direccion : String? = "",
    var latitud: String? = "",
    var longitud : String? = "",
    var tipoTarjeta : String? = "",
    var nroTarjeta : String? = "",
    var vigenciaMes : String? = "",
    var vigenciaAnio : String? = "",
    var nombreTitularTarjeta : String? = ""

) : Parcelable