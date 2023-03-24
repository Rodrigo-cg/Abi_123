package com.example.appqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.appqr.databinding.ActivityDetalleCertificadosBinding
import com.example.appqr.databinding.ActivityScanInspectorBinding
import com.example.appqr.databinding.DetalleInspectorBinding

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