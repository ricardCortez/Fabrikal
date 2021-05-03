package com.example.fabrikal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditCard(
    var nombreTitular : String?,
    var vigenciaMes : String?,
    var vigenciaAnio : String?,
    var nroTarjeta : String?
) : Parcelable