package com.example.appqr

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar

import com.example.appqr.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import java.net.URI
import java.util.Objects

class MainActivity : AppCompatActivity() {

    private lateinit var   binding:ActivityMainBinding
    private lateinit var datos:String
    private lateinit var  datosUser:Map<String, Objects>
    private lateinit var tolls:Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        tolls = findViewById(R.id.mytoolbar)
        tolls.setTitle("APP QR")
        setSupportActionBar(tolls)
         binding.btnScan.setOnClickListener(){
             initScan()
         }
        binding.btnBuscar.setOnClickListener(){
            buscarDatos()
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
                binding.etDatos.setText(datos)


            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    //val activityLauncher =  registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    private fun buscarDatos(){
        val db = Firebase.firestore
  //      val datosUser = db.collection("user")
//        val query1 = datosUser.whereEqualTo("codigo",binding.tvDatos).get()

        db.collection("user")
            .whereEqualTo("codigo", binding.etDatos.text.toString())
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    binding.tvNombres.setText(document.data["nombre"].toString())
                    binding.tvApellidos.setText(document.data["apellido"].toString())

                    if(document.data["url"].toString().length == 0){
                        binding.etCerti.setText("No cuenta con certificado")

                    }else{

                        binding.etCerti.setText(document.data["url"].toString())
                    }

                    binding.etCerti.setOnClickListener(){

                        buscarUrl(document.data["url"].toString())}

                    println("datos mapeo----------------------- ${document.data["url"].toString().length}")
                    Log.d(TAG, "${document.id} => ${document.data}")



                }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        //nombresU = datosUser["nombres"].toString()

        //binding.tvNombres.setText(nombresU)
    }

    private fun buscarUrl(links: String) {

            val intent =  Intent(Intent.ACTION_VIEW,Uri.parse("$links"))
            startActivity(intent)

    }


}