package com.mda.ateinspeccion.model

import com.google.gson.annotations.SerializedName

data class dataUser(
    @SerializedName("id_user") val id_user: String? = null,
    @SerializedName("clave") val clave: String? = null,
    @SerializedName("estado") val estado: String? = null,
    @SerializedName("ultima_conexion") val ultima_conexion: String? = null,
    @SerializedName("Error") val Error: String? = null,

    )
