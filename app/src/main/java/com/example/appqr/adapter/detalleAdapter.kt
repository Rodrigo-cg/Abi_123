package com.example.appqr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appqr.Certificados
import com.example.appqr.R

class detalleAdapter(private val detalleList:ArrayList<Certificados>) :  RecyclerView.Adapter<detalleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): detalleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  detalleViewHolder(inflater.inflate(R.layout.lista_detalle,parent,false))
    }

    override fun getItemCount(): Int {
        return detalleList.size
    }

    override fun onBindViewHolder(holder: detalleViewHolder, position: Int) {
        val item = detalleList[position]
        holder.render(item)

    }

}