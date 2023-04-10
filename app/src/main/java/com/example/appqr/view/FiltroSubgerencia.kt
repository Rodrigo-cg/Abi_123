package com.example.appqr.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appqr.R
import com.example.appqr.adapter.CustomAdapter
import com.example.appqr.databinding.ActivityFiltro1SubgerenciaBinding
import com.example.appqr.model.dataCert

class filtroSubgerencia : AppCompatActivity() {
    private lateinit var   binding: ActivityFiltro1SubgerenciaBinding
    private lateinit var  adapterCert: CustomAdapter
    val listcertxfasociate : ArrayList<dataCert> = intent.getParcelableExtra("workoutlist")!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtro_1_subgerencia)

        binding = ActivityFiltro1SubgerenciaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.recyclerview.layoutManager=LinearLayoutManager(this)

        // This will pass the ArrayList to our Adapter
        //adapterCert= CustomAdapter(this,listcertxfasociate)

        // Setting the Adapter with the recyclerview
        //binding.recyclerview.adapter = adapterCert
    }
}