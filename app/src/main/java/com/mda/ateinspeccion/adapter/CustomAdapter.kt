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

abstract class CustomAdapter(context: Context, var mList: ArrayList<dataCert>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(),
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
        val textView: TextView = itemView.findViewById(R.id.textViewCardfilter1)
    }

    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }
}