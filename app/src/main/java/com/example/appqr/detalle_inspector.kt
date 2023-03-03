package com.example.appqr

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.example.appqr.databinding.DetalleInspectorBinding
import java.util.*


class detalle_inspector : AppCompatActivity() {
    private lateinit var   binding:DetalleInspectorBinding

    private lateinit var tolls: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetalleInspectorBinding.inflate(layoutInflater)

        setContentView(binding.root)
        tolls = findViewById(R.id.mytoolbar)
        tolls.setTitle("Detalle QR")
        setSupportActionBar(tolls)
        binding.btnSeach.setOnClickListener(){

        }

    }
}