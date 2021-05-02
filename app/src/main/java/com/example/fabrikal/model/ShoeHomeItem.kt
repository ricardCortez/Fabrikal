package com.example.fabrikal.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class ShoeHomeItem(
    var productoID : String? = null,
    var marca : String? = null,
    var modelo: String? = null,
    var precio : Double?  = null,
    var tipo : String? = null,
    var color : String? = null,
    var imagen : String?  = null
) : Parcelable