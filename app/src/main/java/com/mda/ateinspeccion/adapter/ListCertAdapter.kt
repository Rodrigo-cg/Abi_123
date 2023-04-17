package com.mda.ateinspeccion.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mda.ateinspeccion.R
import com.mda.ateinspeccion.model.dataList

class ListCertAdapter(context: Context, dataArrayList: ArrayList<dataList?>) :
    ArrayAdapter<dataList?>(context, R.layout.listitem, dataArrayList!!) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        val listData = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false)
        }
        //val listImage = view!!.findViewById<ImageView>(R.id.listImage)
        val Tipo_parametro = view?.findViewById<TextView>(R.id.part_cert_name)
        val parametro = view?.findViewById<TextView>(R.id.par_cert)
        //listImage.setImageResource(listData!!.image)
        Tipo_parametro?.text = listData?.Tipo_parametro
        parametro?.text = listData?.parametro
        return view!!
    }
}