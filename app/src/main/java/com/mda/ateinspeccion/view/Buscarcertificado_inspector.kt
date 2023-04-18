package com.mda.ateinspeccion.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import com.mda.ateinspeccion.R
import com.mda.ateinspeccion.databinding.ActivityBuscarcertificadoInspectorBinding

class buscarcertificado_inspector : AppCompatActivity() {
    private lateinit var binding: ActivityBuscarcertificadoInspectorBinding
    private  lateinit var tolls:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscarcertificado_inspector)

        binding=ActivityBuscarcertificadoInspectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tolls = findViewById(R.id.topAppBar5)

        tolls.setNavigationOnClickListener(){

            finish()

        }
    }

}