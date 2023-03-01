package com.example.appqr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.appqr.databinding.ActivityScanCiudadanoBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import java.util.*

class Scan_ciudadano : AppCompatActivity() {
    private lateinit var   binding: ActivityScanCiudadanoBinding
    private lateinit var datos:String
    private lateinit var tolls: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanCiudadanoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        tolls = findViewById(R.id.mytoolbar)
        tolls.setTitle("APP QR")
        setSupportActionBar(tolls)
        binding.btnScan.setOnClickListener(){
            initScan()
        }

    }
    private  fun initScan(){
        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val resultado = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)

        if(resultado != null){
            if(resultado.contents == null){
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Valor del scanner ${resultado.contents}", Toast.LENGTH_SHORT).show()
                datos = resultado.contents
                buscarDatos(datos)

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    //val activityLauncher =  registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    private fun buscarDatos(dato:String){
        val db = Firebase.firestore
        //      val datosUser = db.collection("user")
//        val query1 = datosUser.whereEqualTo("codigo",binding.tvDatos).get()

        db.collection("user")
            .whereEqualTo(FieldPath.documentId(),dato)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {


                    if(document.data["url"].toString().length == 0){
                        binding.scanResultado.setText("No cuenta con certificado")

                    }else{

                        binding.scanResultado.setText("Si cuenta con certificado")
                        if(document.data["fecha vigencia"].toString().length == 0) {
                            var date = document . getDate ("fecha vigencia")
                            binding.fechavigencia.setText(date.toString())
                        }
                    }

                    if (document == null){
                        binding.scanResultado.setText("Codgi QR de certificado no valido")

                    }





                }

            }
            .addOnFailureListener { exception ->

                    binding.scanResultado.setText("Error al cargar el documento")


            }

        //binding.tvNombres.setText(nombresU)
    }




}