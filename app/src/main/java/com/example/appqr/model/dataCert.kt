package com.example.appqr.model

import com.google.gson.annotations.SerializedName

data class dataCert(
    @SerializedName("Estado") val Estado: String? = null,
    @SerializedName("Lic_Func") val Lic_Func: String? = null,
    @SerializedName("Nombre_Razon_Social") val Nombre_Razón_Social: String? = null,
    @SerializedName("Direccion") val Direccion: String? = null,
    @SerializedName("Zona_Urbana") val Zona_Urbana: String? = null,
    @SerializedName("Res") val Num_Res: String? = null,
    @SerializedName("Esp.") val Num_Exp: String? = null,
    @SerializedName("Giro") val Giro: String? = null,
    @SerializedName("Area") val Area: String? = null,
    @SerializedName("Fech_Exp") val Fecha_Exp: String? = null,
    @SerializedName("Beneficio") val Fecha_Caducidad: String? = null,


    )