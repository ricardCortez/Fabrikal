package com.example.fabrikal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAddress (
var telefono : String?,
var direccion : String?,
var tipoDireccion : String?,
var nroLote: String?,
var provincia: String?,
var urbanizacion : String?,
var distrito : String?,
var latitud: String?,
var longitud : String?) : Parcelable