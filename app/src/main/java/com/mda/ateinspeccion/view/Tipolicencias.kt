package com.mda.ateinspeccion.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.mda.ateinspeccion.databinding.ActivityTipolicenciasBinding
import com.mda.ateinspeccion.R
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
            elecciontramite.tipo=elecciontramite.temporal
            initActivity()


        }
        binding.indeter.setOnClickListener(){
            elecciontramite.tipo=elecciontramite.indeterminada
            initActivity()
        }
        binding.itse.setOnClickListener(){
            elecciontramite.tipo=elecciontramite.itcse
            initActivity()

        }
        binding.ecse.setOnClickListener(){
            elecciontramite.tipo=elecciontramite.ecse
            initActivity()

        }
        binding.construc.setOnClickListener(){
            elecciontramite.tipo=elecciontramite.contrusccion
            initActivity()

        }
        binding.habilitacion.setOnClickListener(){
            elecciontramite.tipo=elecciontramite.habilitacion
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