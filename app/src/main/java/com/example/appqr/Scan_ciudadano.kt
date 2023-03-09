package com.example.appqr

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.appqr.databinding.ActivityScanCiudadanoBinding
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.*

class Scan_ciudadano : AppCompatActivity() {
    private lateinit var   binding: ActivityScanCiudadanoBinding
    private lateinit var datos:String
    private lateinit var tolls: Toolbar

    @SuppressLint("UseCompatLoadingForDrawables")
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

        binding = ActivityScanCiudadanoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        tolls = findViewById(R.id.mytoolbar)
        tolls.setTitle("APP QR CIUDADANO ")
        setSupportActionBar(tolls)
        binding.constraintLayout2.background=resources.getDrawable(R.drawable.btn3)

        binding.btnScan.setOnClickListener(){
            binding.fecharesult.setText("")

            initScan()
        }

        binding.etTexto.setEndIconOnClickListener(){
            buscarCertificado(binding.etNumeros.text.toString())
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
    private fun buscarDatos(dato:String){
        val db = Firebase.firestore
        binding.constraintLayout2.background=resources.getDrawable(R.drawable.btn3)

        //      val datosUser = db.collection("user")
//        val query1 = datosUser.whereEqualTo("codigo",binding.tvDatos).get()

        db.collection("user")
            .whereEqualTo(FieldPath.documentId(),dato)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {


                    if(document.data["url"].toString().length == 0){
                        binding.estadoresult.setText("No cuenta con certificado")
                        binding.fecharesult.setText("")
                        binding.constraintLayout2.setBackgroundColor(Color.parseColor("#cb0e00"))


                    }else{

                        if(document.data["fecha vigencia"]!=null) {

                            var date = document . getDate ("fecha vigencia")
                            val currentTime = Calendar.getInstance().time
                                if (currentTime <= date) {
                                binding.estadoresult.setText("Certificado activo ")
                                   val sdf = SimpleDateFormat("dd/MM/yy")
                                    val current = sdf.format(date)
                                    val cal = Calendar.getInstance()
                                    cal.time = currentTime
                                    val day1 = cal.get(Calendar.DAY_OF_YEAR)
                                    val diff: Long = (date?.getTime() ?:currentTime.getTime()) - currentTime.getTime()
                                    val seconds = diff / 1000
                                    val minutes = seconds / 60
                                    val hours = minutes / 60
                                    val days = hours / 24
                                    binding.fecharesult.setText(current)
                                    binding.constraintLayout2.setBackgroundColor(Color.parseColor("#609f1c"))
                                }
                                else{
                                    binding.estadoresult.setText("Certificano inactivo por fecha de vigencia")
                                    val sdf = SimpleDateFormat("dd/MM/yy")
                                    val current = sdf.format(date)
                                    binding.fecharesult.setText(current)
                                    binding.constraintLayout2.setBackgroundColor(Color.parseColor("#cb0e00"))

                                }


                        }
                                else {
                            binding.estadoresult.setText("Certificado sin fecha actualizada")
                            binding.fecharesult.setText("")
                            binding.constraintLayout2.setBackgroundColor(Color.parseColor("#cb0e00"))

                        }


                    }

                    if (document == null){
                        binding.estadoresult.setText("Codgi QR de certificado no valido")
                        binding.fecharesult.setText("")
                        binding.constraintLayout2.setBackgroundColor(Color.parseColor("#cb0e00"))



                    }





                }

            }
            .addOnFailureListener { exception ->

                    binding.estadoresult.setText("Error al cargar el documento")
                    binding.constraintLayout2.setBackgroundColor(Color.parseColor("#cb0e00"))



            }

        //binding.tvNombres.setText(nombresU)
    }

    private fun buscarCertificado(numero:String){
        val db = Firebase.firestore
        binding.constraintLayout2.background=resources.getDrawable(R.drawable.btn3)

        //      val datosUser = db.collection("user")
//        val query1 = datosUser.whereEqualTo("codigo",binding.tvDatos).get()

        db.collection("user")
            .whereEqualTo("codigo",numero)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {


                    if(document.data["url"].toString().length == 0){
                        binding.estadoresult.setText("No cuenta con certificado")
                        binding.fecharesult.setText("")
                        binding.constraintLayout2.setBackgroundColor(Color.parseColor("#cb0e00"))



                    }else{

                        if(document.data["fecha vigencia"]!=null) {

                            var date = document . getDate ("fecha vigencia")
                            val currentTime = Calendar.getInstance().time
                            if (currentTime <= date) {
                                binding.estadoresult.setText("Certificado activo ")
                                val sdf = SimpleDateFormat("dd/MM/yy")
                                val current = sdf.format(date)
                                binding.fecharesult.setText(current)
                                binding.constraintLayout2.setBackgroundColor(Color.parseColor("#609f1c"))

                            }
                            else{
                                binding.estadoresult.setText("Certificano inactivo por fecha de vigencia")
                                val sdf = SimpleDateFormat("dd/MM/yy")
                                val current = sdf.format(date)
                                binding.fecharesult.setText(current)
                                binding.constraintLayout2.setBackgroundColor(Color.parseColor("#cb0e00"))

                            }


                        }
                        else {
                            binding.estadoresult.setText("Certificado sin fecha actualizada")
                            binding.fecharesult.setText("")
                            binding.constraintLayout2.setBackgroundColor(Color.parseColor("#cb0e00"))

                        }


                    }

                    if (document == null){
                        binding.estadoresult.setText("Codgi QR de certificado no valido")
                        binding.fecharesult.setText("")
                        binding.constraintLayout2.setBackgroundColor(Color.parseColor("#cb0e00"))


                    }





                }

            }
            .addOnFailureListener { exception ->

                binding.estadoresult.setText("Error al cargar el documento")


            }

    }



}