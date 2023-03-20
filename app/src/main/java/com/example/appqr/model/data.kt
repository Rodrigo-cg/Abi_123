package com.example.appqr.model

import com.google.gson.annotations.SerializedName

data class data(
    @SerializedName("Estado") val Estado: String? = null,
    @SerializedName("Lic_Func") val Lic_Func: String? = null,
    @SerializedName("Nombre_Razón_Social") val Nombre_Razón_Social: String? = null,
    @SerializedName("Direccion") val Direccion: String? = null,
    @SerializedName("Zona_Urbana") val Zona_Urbana: String? = null,
)