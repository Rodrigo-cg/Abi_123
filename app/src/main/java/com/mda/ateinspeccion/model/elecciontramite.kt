package com.mda.ateinspeccion.model

import java.sql.Timestamp

object elecciontramite {
    var usuario:String?="No hay usuario"
    val timeStamp = Timestamp(System.currentTimeMillis())
    var temporal: Int? = 0
    var indeterminada: Int? = 0
    var itcse: Int? = 0
    var ecse: Int? = 0
    var contrusccion: Int? = 0
    var habilitacion: Int? = 0

    fun clear(){
        var temporal = 0
        var indeterminada= 0
        var itcse= 0
        var ecse = 0
        var contrusccion= 0
        var habilitacion = 0


    }

}