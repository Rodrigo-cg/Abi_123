package com.mda.ateinspeccion.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mda.ateinspeccion.R
import com.mda.ateinspeccion.model.dataCert

class detalleAdapter1(private val detalleList:List<dataCert>,private val onClickListener: (dataCert)->Unit) :  RecyclerView.Adapter<detalleAdapter1.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cardview1filtro,parent,false)
            return ViewHolder(inflater)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = detalleList[position]

        // sets the image to the imageview from our itemHolder class
        // sets the text to the textview from our itemHolder class
        holder.render(ItemsViewModel, onClickListener)
        if(ItemsViewModel.Estado.equals("VIGENTE")){
            holder.layout.setBackgroundResource(R.drawable.estadoactivo)
            holder.lic.setTextColor(Color.parseColor("#000000"))
            holder.exp.setTextColor(Color.parseColor("#000000"))
            holder.razon_nom.setTextColor(Color.parseColor("#000000"))
            holder.estado.setTextColor(Color.parseColor("#000000"))
        }else{
            holder.layout.setBackgroundResource(R.drawable.estadoinactivo)
            holder.lic.setTextColor(Color.parseColor("#FFFFFF"))
            holder.exp.setTextColor(Color.parseColor("#FFFFFF"))
            holder.razon_nom.setTextColor(Color.parseColor("#FFFFFF"))
            holder.estado.setTextColor(Color.parseColor("#FFFFFF"))

        }
    }

    override fun getItemCount(): Int {
        return detalleList.size
    }

      // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.Lic)
        val lic: TextView = itemView.findViewById(R.id.Lic)
        val exp: TextView = itemView.findViewById(R.id.exp2)
        val razon_nom: TextView = itemView.findViewById(R.id.nom_razon)
        val estado: TextView = itemView.findViewById(R.id.estado)
        val layout: ConstraintLayout=itemView.findViewById(R.id.constraint1)
        fun render(certificado_item: dataCert, oncClickListener: (dataCert) -> Unit) {
            lic.text = certificado_item.Lic_Func
            exp.text = certificado_item.Num_Exp

            razon_nom.text = certificado_item.Nombre_Razón_Social
            estado.text = certificado_item.Estado

            itemView.setOnClickListener {
                oncClickListener(certificado_item)

            }
        }
    }

}