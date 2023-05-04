package com.mda.ateinspeccion

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mda.ateinspeccion.adapter.detalleAdapter

import com.mda.ateinspeccion.databinding.DetalleInspectorBinding

import java.util.*
import kotlin.collections.ArrayList


class detalle_inspector : AppCompatActivity() {
    private lateinit var   binding: DetalleInspectorBinding
    private  var  contador = 0
    private lateinit var  certiList:ArrayList<Certificados>
    private lateinit var  adapterCerti: detalleAdapter
    private lateinit var  builder:AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetalleInspectorBinding.inflate(layoutInflater)


        setContentView(binding.root)

        binding.btnSeach.setOnClickListener(){
           // buscarCertificados(binding.etDNI.text.toString())
            contador = 0
        }

    }

    /*fun buscarCertificados(dato:String){
        val db = Firebase.firestore
        certiList = ArrayList()
        adapterCerti = detalleAdapter(certiList)
/*
        db.collection("user")
            .whereEqualTo("codigo", dato)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
        */
        db.collection("empresa")
            .whereEqualTo("dni",dato)
            .get()
            .addOnSuccessListener { documents ->
                for(document in documents){
                    if(document == null ){
                        println("SAPOSSSSSSS------------------------")
                        Toast.makeText(this,"No hay datos", Toast.LENGTH_SHORT).show()
                    }else{
                        println("SAPOSSSSSSS------------------------22222")
                        val cerItem = document.toObject(Certificados::class.java)
                        cerItem.razon = document["razon"].toString()
                        cerItem.representante = document["representante"].toString()
                        cerItem.direccion = document["direccion"].toString()
                        cerItem.actividad = document["actividad"].toString()
                        cerItem.fechaVencimiento = document["fecvencer"].toString()
                        cerItem.vigencia = document["vigencia"].toString()


                        binding.rvResultados.layoutManager = LinearLayoutManager(this)
                        binding.rvResultados.adapter = adapterCerti
                        certiList.add(cerItem)

                    }
                    contador++
                    Toast.makeText(this,"Coincidencias ${contador}", Toast.LENGTH_SHORT).show()
                    //val certList: MutableMap<String, Any> = document.data
                    Log.i("listadoosss", document.data.toString())
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    println(certiList)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }*/
}