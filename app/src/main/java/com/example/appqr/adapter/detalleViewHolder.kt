package com.example.appqr.adapter

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appqr.Certificados
import com.example.appqr.R

class detalleViewHolder (view:View): ViewHolder(view){

    //val img = view.findViewById<>()
    var razon = view.findViewById<TextView>(R.id.tvRazon)
    var repre = view.findViewById<TextView>(R.id.tvRepresentante)
    var direc = view.findViewById<TextView>(R.id.tvDireccion)
    var actividad = view.findViewById<TextView>(R.id.tvActvidad)
    var fecVencimiento = view.findViewById<TextView>(R.id.tvFecVencimiento)
    var vigencia = view.findViewById<TextView>(R.id.tvVigencia)


    fun render(certificados: Certificados){
        razon.text = certificados.razon
        repre.text = certificados.representante
        direc.text = certificados.direccion
        actividad.text = certificados.actividad
        fecVencimiento.text = certificados.fechaVencimiento
        vigencia.text = certificados.vigencia

    }
}