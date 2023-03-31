package com.example.appqr.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.appqr.R
import com.example.appqr.databinding.ActivityScanCiudadanoBinding
import com.example.appqr.model.apiService
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class Scan_ciudadano : AppCompatActivity() {
    private lateinit var   binding: ActivityScanCiudadanoBinding
    private lateinit var datos:String
    private lateinit var tolls: Toolbar

    private var Estado= ""
    private var Lic_func= ""
    private var Nombre_Razon= ""
    private var direccion= ""
    private var zona= ""
    private var Num_Res= ""
    private var  Num_Exp= ""
    private var  Giro= ""
    private var  Area= ""
    private var  Fecha_Exp= ""
    private var  Fecha_Caducidad= ""

    @SuppressLint("ResourceAsColor")
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
        binding.fecharesult1.setText("")
        binding.constraintLayout3.setBackgroundResource(R.drawable.btn4)
        setSupportActionBar(tolls);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false);
        //getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.baseline_arrow_left_24)
        //getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(android.R.color.transparent)));
        tolls.setBackgroundColor(android.R.color.transparent)
        tolls.setNavigationIcon(R.drawable.baseline_arrow_left_24)
        binding.btnScan.setOnClickListener(){
            binding.fecharesult1.setText("")
            binding.constraintLayout3.setBackgroundResource(R.drawable.btn4)

            initScan()
        }

        binding.btnlupa.setOnClickListener(){
            //Toast.makeText(this , "date", Toast.LENGTH_SHORT).show()
            binding.fecharesult1.setText("")
            binding.constraintLayout3.setBackgroundResource(R.drawable.btn4)
            buscarCertificado(binding.etNumeros.text.toString())

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
                buscarCertificado(datos)


            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    //val activityLauncher =  registerForActivityResult(ActivityResultContracts.StartActivityForResult())

            @SuppressLint("ResourceAsColor")
            private fun buscarCertificado(dato:String){
                getRetrofit()

                CoroutineScope(Dispatchers.IO).launch {
                    GlobalScope.launch {
                        val result = getRetrofit().create(apiService::class.java). getDataCert("certificados_apps/conexiones_php/consultar.php?LIC=$dato")
                        //     val result = getRetrofit().create(apiService::class.java). getDataCert(dato)

                        val certpar=result.body()
                        runOnUiThread{
                            if (result != null) {
                                // Checking the results
                                Log.d("ayush: ", result.body().toString())
                                Estado= certpar?.Estado ?:"No exite en base de datos"
                                Lic_func= certpar?.Lic_Func ?:"No exite en base de datos"
                                Nombre_Razon= certpar?.Nombre_Razón_Social ?:"No exite en base de datos"
                                direccion= certpar?.Direccion ?:"No exite en base de datos"
                                zona           = certpar?.Zona_Urbana ?:"No exite en base de datos"
                                Num_Res         =  certpar?.Num_Res?:"No exite en base de datos"
                                Num_Exp         = certpar?.Num_Exp?:"No exite en base de datos"
                                Giro            = certpar?.Giro?:"No exite en base de datos"
                                Area            = certpar?.Area?:"No exite en base de datos"
                                Fecha_Exp       = certpar?.Fecha_Exp?:"No exite en base de datos"
                                Fecha_Caducidad=certpar?.Fecha_Caducidad?:"No exite en base de datos"

                                if(Lic_func.equals(dato)){
                                    if(Estado.equals("VIGENTE")){
                                        binding.constraintLayout3.setBackgroundResource(R.drawable.estadoactivo)
                                        //binding.estadocertifi.setTextColor(R.color.white)
                                        binding.fecharesult1.setText(Fecha_Exp)
                                        val passwordLayout: TextInputLayout =findViewById(R.id.textInputLayout)
                                        passwordLayout.error = null
                                    }
                                    else {
                                        binding.constraintLayout3.setBackgroundResource(R.drawable.estadoinactivo)
                                        binding.fecharesult1.setText(Fecha_Exp)
                                        val passwordLayout: TextInputLayout =findViewById(R.id.textInputLayout)
                                        passwordLayout.error = null
                                    }

                                }
                                else
                                {
                                    val passwordLayout: TextInputLayout =findViewById(R.id.textInputLayout)
                                    passwordLayout.error = "Datos incorrectos"
                                }



                                binding.estadoresult.setText(Estado)
                            }else
                                Toast.makeText(applicationContext, "No se recibe ningun", Toast.LENGTH_SHORT).show()
                        }


                    }
                }

            }

            private fun getRetrofit(): Retrofit {
                return Retrofit.Builder()
            .baseUrl("https://proyectosti.muniate.gob.pe/")
            //.baseUrl("https://delorekbyrnison.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
       }





