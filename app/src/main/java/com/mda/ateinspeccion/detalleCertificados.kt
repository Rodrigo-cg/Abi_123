package com.mda.ateinspeccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mda.ateinspeccion.databinding.ActivityDetalleCertificadosBinding

class detalleCertificados : AppCompatActivity() {
    private lateinit var   binding: ActivityDetalleCertificadosBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleCertificadosBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val Estado = intent.getStringExtra("Estado")
        val lic_func = intent.getStringExtra("lic_func")
        val Nombre_razon = intent.getStringExtra("Nombre_razon")
        val Direccion = intent.getStringExtra("Direccion")
        val Zona = intent.getStringExtra("Zona")

        binding.Estado.setText(Estado)
        binding.licFunc1.setText(lic_func)
        binding.nombreRazonsocial.setText(Nombre_razon)
        binding.direccionzona.setText("$Direccion - $Zona")
    }
}