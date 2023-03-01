package com.example.appqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appqr.databinding.ActivityInicioBinding

class Inicio : AppCompatActivity() {

    private lateinit var binding: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.btnIngresarAuth.setOnClickListener(){
            val intent = Intent(this, Scan_inspector::class.java)
            startActivity(intent)
        }
    }


}