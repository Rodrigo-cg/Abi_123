package com.example.appqr.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appqr.R
import com.example.appqr.adapter.CustomAdapter
import com.example.appqr.databinding.ActivityFiltro1SubgerenciaBinding
import com.example.appqr.model.ItemsViewModel

class filtroSubgerencia : AppCompatActivity() {
    private lateinit var   binding: ActivityFiltro1SubgerenciaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtro_1_subgerencia)

        binding = ActivityFiltro1SubgerenciaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.recyclerview.layoutManager=LinearLayoutManager(this)
        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            data.add(ItemsViewModel( "Item " + i))
        }
        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        binding.recyclerview.adapter = adapter
    }
}