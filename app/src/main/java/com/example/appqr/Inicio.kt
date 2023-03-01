package com.example.appqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.appqr.databinding.ActivityInicioBinding

class Inicio : AppCompatActivity() {

    private lateinit var binding: ActivityInicioBinding
    private lateinit var datos:String
    private lateinit var tolls: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)

        setContentView(binding.root)
        tolls = findViewById(R.id.mytoolbar)
        tolls.setTitle("APP QR ")
        setSupportActionBar(tolls)
        binding.btnIngresarAuth.setOnClickListener(){
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        binding.btnIngresarCiudadano.setOnClickListener(){
            val intent = Intent(this, Scan_ciudadano::class.java)
            startActivity(intent)
        }

    }


}