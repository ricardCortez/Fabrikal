package com.example.fabrikal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrdenCompra(
    var ordenID : String? = null,
    var nombre : String? = null,
    var direccion : String? = null,
    var telefono : String? = null,
    var productoID : String? = null,
    var cantidad : String? = "1",
    var modelo : String? = null,
    var precio : Double? = null,
    var tipoEnvio : String? = null, // TIENDA, DOMICILIO
    var metodoPago : String? = null,
    var tokenPago : String? = null,//TOKEN VISA
    var latDomicilio : String? = null,
    var longDomicilio : String? = null
) : Parcelable