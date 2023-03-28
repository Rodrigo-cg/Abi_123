package com.example.appqr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appqr.adapter.ListCertAdapter
import com.example.appqr.databinding.ActivityListBinding
import com.example.appqr.model.dataList

class list : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var listAdapter: ListCertAdapter
    private lateinit var listData: dataList


    var Estado= intent.getStringExtra("Estado")
    var lic_func= intent.getStringExtra("lic_func")
    var Nombre_razon= intent.getStringExtra("Nombre_razon")
    var Direccion= intent.getStringExtra("Direccion")
    var Zona= intent.getStringExtra("Zona")
    var Num_Res= intent.getStringExtra("Num_Res")
    var Num_Exp= intent.getStringExtra("Num_Exp")
    var Giro= intent.getStringExtra("Giro")
    var Area= intent.getStringExtra("Area")
    var Fecha_Exp= intent.getStringExtra("Fecha_Exp")
    var Fecha_Caducidad= intent.getStringExtra("Fecha_Caducidad")
    var dataArrayList = ArrayList<dataList?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val nameList = arrayOf("Nombre", "Giro", "Àrea", "Direccioòn", "Fecha de Expediciòn", "Número de Expediente", "Nùmero de Resoluciòn", "Nùmero de Certificado","Fecha de caducidad")
        val timeList = arrayOf(Nombre_razon, Giro, Area, Direccion, Fecha_Exp, Num_Exp, Num_Res, lic_func,Fecha_Caducidad)
        for (i in nameList.indices) {
            listData = dataList(
                nameList[i],
                timeList[i]
            )
            dataArrayList.add(listData)
        }
        listAdapter = ListCertAdapter(this, dataArrayList)
        binding.listview.adapter = listAdapter

        }



}


