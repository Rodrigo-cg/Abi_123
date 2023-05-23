package com.mda.ateinspeccion.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mda.ateinspeccion.R
import com.mda.ateinspeccion.adapter.ListCertAdapter
import com.mda.ateinspeccion.databinding.ActivityListBinding
import com.mda.ateinspeccion.model.dataList

class list : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var listAdapter: ListCertAdapter
    private lateinit var listData: dataList
    private lateinit var tolls: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var Estado=  intent.getStringExtra("Estado").toString()
        var lic_func= intent.getStringExtra("lic_func").toString()
        var Nombre_razon= intent.getStringExtra("Nombre_razon").toString()
        var Direccion= intent.getStringExtra("Direccion").toString()
        var Zona= intent.getStringExtra("Zona").toString()
        var Num_Res= intent.getStringExtra("Num_Res").toString()
        var Num_Exp= intent.getStringExtra("Num_Exp").toString()
        var Giro= intent.getStringExtra("Giro").toString()
        var Area= intent.getStringExtra("Area").toString()
        var Fecha_Exp= intent.getStringExtra("Fecha_Exp").toString()
        var Fecha_Caducidad= intent.getStringExtra("Fecha_Caducidad").toString()
        var dataArrayList = ArrayList<dataList?>()


        tolls = findViewById(R.id.topAppBar4)
        tolls.setNavigationOnClickListener(){

            finish()

        }


        val nameList = arrayOf("Nombre", "Giro", "Área", "Dirección", "Fecha de Expedición", "Número de Expediente", "Número de Resolución", "Número de Certificado","Fecha de caducidad")
        val timeList = arrayOf(Nombre_razon, Giro, Area +" m2", Direccion, Fecha_Exp, Num_Exp, Num_Res, lic_func,Fecha_Caducidad + "\n")
        for (i in nameList.indices) {
            listData = dataList(
                nameList[i],
                timeList[i]
            )
           dataArrayList.add(listData)
        }
        listAdapter = ListCertAdapter(this, dataArrayList)
        binding.listview.adapter = listAdapter
  //
        //Toast.makeText(this,"$lic_func --- $Fecha_Caducidad ----",Toast.LENGTH_SHORT).show()
        }



}


