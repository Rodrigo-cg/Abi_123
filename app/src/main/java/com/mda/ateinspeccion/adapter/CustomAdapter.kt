package com.mda.ateinspeccion.adapter

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mda.ateinspeccion.R
import com.mda.ateinspeccion.model.dataCert

abstract class CustomAdapter(context: Context, var mList: ArrayList<dataCert>, private val oncClickListener:(dataCert)-> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(),
    ListAdapter {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview1filtro, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dataCert = mList[position]
        holder.render(dataCert, oncClickListener)

        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class
        holder.textView.text = dataCert.Nombre_Razón_Social

    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.Lic)
        val lic: TextView = itemView.findViewById(R.id.Lic)
        val exp: TextView = itemView.findViewById(R.id.exp2)
        val razon_nom: TextView = itemView.findViewById(R.id.nom_razon)
        val estado: TextView = itemView.findViewById(R.id.estado)
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


