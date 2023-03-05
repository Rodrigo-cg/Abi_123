package com.example.appqr

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import com.example.appqr.databinding.ActivityScanInspectorBinding
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import org.checkerframework.common.returnsreceiver.qual.This
import java.text.SimpleDateFormat
import java.util.*

class Scan_inspector : AppCompatActivity() {

    private lateinit var   binding:ActivityScanInspectorBinding
    private lateinit var datos:String
    private lateinit var  datosUser:Map<String, Objects>
    private lateinit var tolls:Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanInspectorBinding.inflate(layoutInflater)

        setContentView(binding.root)
        tolls = findViewById(R.id.mytoolbar)
        tolls.setTitle("APP QR INSPECTOR")
        setSupportActionBar(tolls)
         binding.btnScan.setOnClickListener(){
             binding.visualizar.visibility= View.INVISIBLE
             binding.fecharesult.setText("")

             initScan()
         }

        binding.btnDetalle.setOnClickListener(){
            /*
            *    val intent =  Intent(Intent.ACTION_VIEW,Uri.parse("$links"))
            startActivity(intent)
            * */

            //val intento = Intent(this,detalle_inspector::class.java)
            //startActivity(intento)
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
    @SuppressLint("SuspiciousIndentation")
    private fun buscarDatos(dato: String){

        val db = Firebase.firestore
  //      val datosUser = db.collection("user")
//        val query1 = datosUser.whereEqualTo("codigo",binding.tvDatos).get()

        db.collection("user")
            .whereEqualTo(FieldPath.documentId(),dato)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    if(document.data["url"].toString().length == 0){
                        binding.estadoresult.setText("No cuenta con certificado")
                        binding.visualizar.visibility= View.INVISIBLE
                    }else{
                        if(document.data["fecha vigencia"]!=null) {

                            var date = document . getDate ("fecha vigencia")
                            val currentTime = Calendar.getInstance().time
                            if (currentTime <= date) {
                                binding.estadoresult.setText("Certificado activo ")
                                val sdf = SimpleDateFormat("dd/MM/yy")
                                val current = sdf.format(date)
                                binding.fecharesult.setText(current)
                                binding.visualizar.visibility= View.VISIBLE

                            }
                            else {
                                binding.estadoresult.setText("Certificano inactivo por fecha de vigencia")
                                val sdf = SimpleDateFormat("dd/MM/yy")
                                val current = sdf.format(date)
                                binding.fecharesult.setText(current)
                                binding.visualizar.visibility = View.VISIBLE
                            }

                        }
                        else {
                            binding.estadoresult.setText("Certificado sin fecha actualizada")
                            binding.visualizar.visibility= View.VISIBLE
                            binding.fecharesult.setText("")


                        }


                    }

                    if (document == null){
                        binding.estadoresult.setText("Codgi QR de certificado no valido")
                        binding.fecharesult.setText("")


                    }

                    binding.visualizar.setOnClickListener(){
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