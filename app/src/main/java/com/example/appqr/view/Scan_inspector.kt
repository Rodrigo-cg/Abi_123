package com.example.appqr.view

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.appqr.R

import com.example.appqr.databinding.ActivityScanInspectorBinding
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.*

class Scan_inspector : AppCompatActivity() {

    private lateinit var   binding:ActivityScanInspectorBinding
    private lateinit var datos:String
    private lateinit var  datosUser:Map<String, Objects>
    private lateinit var tolls:Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Bienvenido")
        builder.setMessage("Recuerda verificar y estar atento a la fecha de vigencia")
        builder.setPositiveButton("Aceptar"){
                dialog, which ->
            Toast.makeText(this,"has aceptado", Toast.LENGTH_LONG).show()
        }
        builder.show()

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
        binding.etTexto.setEndIconOnClickListener(){
            buscarCertificado(binding.etNumeros.text.toString())
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
                        binding.constraintLayout3.setBackgroundColor(Color.parseColor("#cb0e00"))

                    }else{
                        if(document.data["fecha vigencia"]!=null) {

                            var date = document . getDate ("fecha vigencia")
                            val currentTime = Calendar.getInstance().time
                            if (currentTime <= date) {
                                val sdf = SimpleDateFormat("dd/MM/yy")
                                val current = sdf.format(date)
                                val diff: Long = (date?.getTime() ?:currentTime.getTime()) - currentTime.getTime()
                                val seconds = diff / 1000
                                val minutes = seconds / 60
                                val hours = minutes / 60
                                val days = hours / 24
                                if (days<30){
                                    binding.estadoresult.setText("Certificado activo ,renovacion urgente ")
                                    binding.constraintLayout3.setBackgroundColor(Color.parseColor("#FF7E00"))

                                }
                                else{
                                    binding.estadoresult.setText("Certificado activo ")
                                    binding.constraintLayout3.setBackgroundColor(Color.parseColor("#609f1c"))
                                }
                                binding.fecharesult.setText(current)
                                binding.visualizar.visibility= View.VISIBLE

                            }
                            else {
                                binding.estadoresult.setText("Certificano inactivo por fecha de vigencia")
                                val sdf = SimpleDateFormat("dd/MM/yy")
                                val current = sdf.format(date)
                                binding.constraintLayout3.setBackgroundColor(Color.parseColor("#cb0e00"))
                                binding.fecharesult.setText(current)
                                binding.visualizar.visibility = View.VISIBLE
                            }

                        }
                        else {
                            binding.estadoresult.setText("Certificado sin fecha actualizada")
                            binding.visualizar.visibility= View.VISIBLE
                            binding.fecharesult.setText("")
                            binding.constraintLayout3.setBackgroundColor(Color.parseColor("#cb0e00"))


                        }


                    }

                    if (document == null){
                        binding.estadoresult.setText("Codgi QR de certificado no valido")
                        binding.fecharesult.setText("")
                        binding.constraintLayout3.setBackgroundColor(Color.parseColor("#cb0e00"))


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
    private fun buscarCertificado(numero: String){

        val db = Firebase.firestore
        //      val datosUser = db.collection("user")
//        val query1 = datosUser.whereEqualTo("codigo",binding.tvDatos).get()

        db.collection("user")
            .whereEqualTo("codigo",numero)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    if(document.data["url"].toString().length == 0){
                        binding.estadoresult.setText("No cuenta con certificado")
                        binding.visualizar.visibility= View.INVISIBLE
                        binding.constraintLayout3.setBackgroundColor(Color.parseColor("#cb0e00"))

                    }else{
                        if(document.data["fecha vigencia"]!=null) {

                            var date = document . getDate ("fecha vigencia")
                            val currentTime = Calendar.getInstance().time
                            if (currentTime <= date) {
                                val sdf = SimpleDateFormat("dd/MM/yy")
                                val current = sdf.format(date)
                                val diff: Long = (date?.getTime() ?:currentTime.getTime()) - currentTime.getTime()
                                val seconds = diff / 1000
                                val minutes = seconds / 60
                                val hours = minutes / 60
                                val days = hours / 24
                                if (days<30){
                                    binding.estadoresult.setText("Certificado activo ,renovacion urgente ")
                                    binding.constraintLayout3.setBackgroundColor(Color.parseColor("#FF7E00"))

                                }
                                else{
                                    binding.estadoresult.setText("Certificado activo ")
                                    binding.constraintLayout3.setBackgroundColor(Color.parseColor("#609f1c"))
                                }
                                binding.fecharesult.setText(current)
                                binding.visualizar.visibility= View.VISIBLE

                            }
                            else {
                                binding.estadoresult.setText("Certificano inactivo por fecha de vigencia")
                                val sdf = SimpleDateFormat("dd/MM/yy")
                                val current = sdf.format(date)
                                binding.constraintLayout3.setBackgroundColor(Color.parseColor("#cb0e00"))
                                binding.fecharesult.setText(current)
                                binding.visualizar.visibility = View.VISIBLE
                            }

                        }
                        else {
                            binding.estadoresult.setText("Certificado sin fecha actualizada")
                            binding.visualizar.visibility= View.VISIBLE
                            binding.fecharesult.setText("")
                            binding.constraintLayout3.setBackgroundColor(Color.parseColor("#cb0e00"))


                        }


                    }

                    if (document == null){
                        binding.estadoresult.setText("Codgi QR de certificado no valido")
                        binding.fecharesult.setText("")
                        binding.constraintLayout3.setBackgroundColor(Color.parseColor("#cb0e00"))


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
       /*//comenzar la api rest de php
        val queue=Volley.newRequestQueue(this)
        val url="https://proyectosti.muniate.gob.pe/certificados_apps/conexiones_php/consultar.php"
        var jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length() ){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro=
                            LayoutInflater.from(this).inflate(R.layout.deployconsulta,null,false)
                        val colNombre=registro.findViewById<View>(R.id.colNombre) as TextView
                        val colEmail=registro.findViewById<View>(R.id.colEmail) as TextView
                        val colEditar=registro.findViewById<View>(R.id.colEditar)
                        val colBorrar=registro.findViewById<View>(R.id.colBorrar)
                        colNombre.text=jsonObject.getString("nombre")
                        colEmail.text=jsonObject.getString("email")
                        colEditar.id=jsonObject.getString("id").toInt()
                        colBorrar.id=jsonObject.getString("id").toInt()
                        tbUsuarios?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            },Response.ErrorListener { error ->

            }
        )
        queue.add(jsonObjectRequest)
        //terminar la api rest de php*/

    }
    private fun buscarUrl(links: String) {

            val intent =  Intent(Intent.ACTION_VIEW,Uri.parse("$links"))
            startActivity(intent)

    }


}