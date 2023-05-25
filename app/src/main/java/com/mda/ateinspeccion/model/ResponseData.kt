package com.mda.ateinspeccion.model

import com.google.gson.annotations.SerializedName

data class ResponseDataSQL_ind_temp(
    @SerializedName("Estado") val Estado: String? = null,
    @SerializedName("Lic_Func") val Lic_Func: String? = null,
    @SerializedName("Nombre_Razon_Social") val Nombre_Razón_Social: String? = null,
    @SerializedName("Direccion") val Direccion: String? = null,
    @SerializedName("Zona_Urbana") val Zona_Urbana: String? = null,
    @SerializedName("Res") val Num_Res: String? = null,
    @SerializedName("Exp") val Num_Exp: String? = null,
    @SerializedName("Giro") val Giro: String? = null,
    @SerializedName("Area") val Area: String? = null,
    @SerializedName("Fech_Exp") val Fecha_Exp: String? = null,
    @SerializedName("Beneficio") val Fecha_Caducidad: String? = null,
    @SerializedName("tipo_db") val tipo_db: String? = null,
    @SerializedName("N° EXPEDIENTE") val exp_sghue: String? = null,
    @SerializedName("RAZON SOCIAL / NOMBRE Y APELLIDO") val nom_razon_sghue: String? = null,
    @SerializedName("TIPO DE RESOLUCION") val tipo_Reso_sghue: String? = null,
    @SerializedName("DESCRIPCION") val descripcion: String? = null,)

data class ResponseDataGoogleSheeets_sghue(
    @SerializedName("N° EXPEDIENTE") val exp_sghue: String? = null,
    @SerializedName("RAZON SOCIAL / NOMBRE Y APELLIDO") val nom_razon_sghue: String? = null,
    @SerializedName("TIPO DE RESOLUCION") val tipo_Reso_sghue: String? = null,
    @SerializedName("DESCRIPCION") val descripcion: String? = null,

    )

data class ResponseDataGoogleSheeets_riesgo_desastre(
    @SerializedName("EXP") val exp_itcse: String? = null,
    @SerializedName("N° RES") val nom_razon_itcse: String? = null,
    @SerializedName("Fecha de Expedición") val fecha_exp_itcse: String? = null,
    @SerializedName("RAZON SOCIAL") val razonsocial_itcse: String? = null,
    @SerializedName("REPRESENTANTE") val represen_itcse: String? = null,
    @SerializedName("AREA") val area_itcse: String? = null,
    @SerializedName("GIRO") val giro_itcse: String? = null,
    @SerializedName("RIESGO") val riesgo_itcse: String? = null,
    @SerializedName("DIRECCION") val direccion_itcse: String? = null,
    @SerializedName("Fecha de Caducidad") val fecha_cad_itcse: String? = null,
)
