package com.mda.ateinspeccion.model

import java.sql.Timestamp

object elecciontramite {

    var usuario:String?="No hay usuario"
    var timeStamp = Timestamp(System.currentTimeMillis())
    val temporal: String?= "temporal"
    val indeterminada: String? = "indeterminada"
    val itcse: String? = "itcse"
    val ecse: String? = "ecse"
    val contrusccion: String? = "contrusccion"
    val habilitacion: String? = "habilitacion"
    var tipo: String?=""



    fun clear(){
        var temporal = 0
        var indeterminada= 0
        var itcse= 0
        var ecse = 0
        var contrusccion= 0
        var habilitacion = 0
        var tipo: String?=""



    }

}