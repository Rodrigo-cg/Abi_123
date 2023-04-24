package com.mda.ateinspeccion.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.mda.ateinspeccion.R
import com.mda.ateinspeccion.databinding.ActivityTipolicenciasBinding
import com.mda.ateinspeccion.model.elecciontramite

class tipolicencias : AppCompatActivity() {
    private lateinit var binding: ActivityTipolicenciasBinding
    private lateinit var tolls:Toolbar
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipolicencias)

        binding= ActivityTipolicenciasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tolls = findViewById(R.id.topAppBar5_1)
        tolls.setNavigationOnClickListener(){

            finish()

        }
        elecciontramite.clear()
        binding.temporal.setOnClickListener(){
            elecciontramite.temporal=1
            initActivity()


        }
        binding.indeter.setOnClickListener(){
            elecciontramite.indeterminada=1
            initActivity()
        }
        binding.itse.setOnClickListener(){
            elecciontramite.itcse=1
            initActivity()

        }
        binding.ecse.setOnClickListener(){
            elecciontramite.ecse=1
            initActivity()

        }
        binding.construc.setOnClickListener(){
            elecciontramite.contrusccion=1
            initActivity()

        }
        binding.habilitacion.setOnClickListener(){
            elecciontramite.habilitacion=1
            initActivity()

        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }

    }

    private fun initActivity() {
        val intent = Intent(this, Scan_inspector::class.java)
        startActivity(intent)

    }


}