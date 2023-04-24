package com.mda.ateinspeccion.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mda.ateinspeccion.Certificados
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
        holder.textView.text = ItemsViewModel.Lic_Func
        if(ItemsViewModel.Estado.equals("VIGENTE")){
            holder.linear.setBackgroundResource(R.drawable.estadoactivo)

        }else{
            holder.linear.setBackgroundResource(R.drawable.estadoinactivo)
        }
    }

    override fun getItemCount(): Int {
        return detalleList.size
    }

      // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewCardfilter1)
        val linear:LinearLayout=itemView.findViewById(R.id.linear1)
    }

}