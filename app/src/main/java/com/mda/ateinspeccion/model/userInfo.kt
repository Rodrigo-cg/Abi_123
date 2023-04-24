package com.mda.ateinspeccion.model

import com.google.gson.annotations.SerializedName

data class userInfo(
    @SerializedName("id") val id: Int?,
    @SerializedName("Usuario") val usuario: String?,
    @SerializedName("fecha_ingreso") val fecha_ingreso: String?,
    @SerializedName("Fecha_busqueda") val fecha_busqueda: String?,
    @SerializedName("N_licencia") val n_licencia: String?,
    @SerializedName("N_exp") val n_exp: String?,
    @SerializedName("Tipo_licencia") val t_lic: String?,
    @SerializedName("Nombre_Razon_social") val nombre_razonsocial: String?

    )
