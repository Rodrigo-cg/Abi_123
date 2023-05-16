package com.mda.ateinspeccion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.mda.ateinspeccion.adapter.detalleAdapter1
import com.mda.ateinspeccion.databinding.ActivityFiltrartiposlicenciaBinding
import com.mda.ateinspeccion.model.dataCert
import com.mda.ateinspeccion.model.objectlistcertlic
import com.mda.ateinspeccion.view.list

class filtrartiposlicencia : AppCompatActivity() {
    private lateinit var binding: ActivityFiltrartiposlicenciaBinding
    private lateinit var adapterCert: detalleAdapter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtrartiposlicencia)
        binding = ActivityFiltrartiposlicenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterCert = detalleAdapter1(objectlistcertlic.mutablelitcert) { certificado ->
            onItemSelected(certificado)
        }
        binding.indetcard.layoutManager = LinearLayoutManager(this)
        binding.indetcard.adapter = adapterCert

        binding.tempcar.layoutManager = LinearLayoutManager(this)
        binding.tempcar.adapter = adapterCert

        binding.itcsecard.layoutManager = LinearLayoutManager(this)
        binding.itcsecard.adapter = adapterCert

        binding.ecsecard.layoutManager = LinearLayoutManager(this)
        binding.ecsecard.adapter = adapterCert

        binding.construcard.layoutManager = LinearLayoutManager(this)
        binding.construcard.adapter = adapterCert

        binding.habilicard.layoutManager = LinearLayoutManager(this)
        binding.habilicard.adapter = adapterCert

    }

    private fun onItemSelected(datacer: dataCert) {
        val i = Intent(this, list::class.java).apply {
            putExtra("Estado", datacer.Estado)
            putExtra("lic_func", datacer.Lic_Func)
            putExtra("Nombre_razon", datacer.Nombre_Razón_Social)
            putExtra("Direccion", datacer.Direccion)
            putExtra("Zona", datacer.Zona_Urbana)
            putExtra("Num_Res", datacer.Num_Res)
            putExtra("Num_Exp", datacer.Num_Exp)
            putExtra("Giro", datacer.Giro)
            putExtra("Area", datacer.Area)
            putExtra("Fecha_Exp", datacer.Fecha_Exp)
            putExtra("Fecha_Caducidad", datacer.Fecha_Caducidad)

        }
        startActivity(i)


    }
}
