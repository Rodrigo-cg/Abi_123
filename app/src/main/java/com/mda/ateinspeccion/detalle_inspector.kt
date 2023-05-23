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



   }