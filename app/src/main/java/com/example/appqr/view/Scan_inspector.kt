package com.example.appqr.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.example.appqr.R

import com.example.appqr.databinding.ActivityScanInspectorBinding
import com.example.appqr.detalleCertificados
import com.example.appqr.model.apiService
import com.example.appqr.model.dataCert
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.annotations.SerializedName
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class Scan_inspector : AppCompatActivity() {

    private lateinit var   binding:ActivityScanInspectorBinding
    private lateinit var datos:String
    private lateinit var  datosUser:Map<String, Objects>
    private lateinit var tolls:Toolbar

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



    private val lista_par_certf = mutableListOf<dataCert>()
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


        //<include layout="@layout/custom_toolbar"></include>

        setContentView(binding.root)
        binding.datacert.visibility= View.INVISIBLE
        //tolls = findViewById(R.id.mytoolbar)
        //tolls.setTitle("APP QR INSPECTOR")
        //setSupportActionBar(tolls)
         binding.btnScan.setOnClickListener(){
             binding.visualizar.visibility= View.INVISIBLE
             binding.fecharesult.setText("")

             initScan()
         }
        binding.btnlupa.setOnClickListener(){
            //buscarCertificado(binding.etNumeros.text.toString())
            buscardatosretrofit(binding.etNumeros.text.toString())
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
                buscardatosretrofit(datos)


            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    //val activityLauncher =  registerForActivityResult(ActivityResultContracts.StartActivityForResult())

    private fun buscardatosretrofit(dato:String){
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
                            binding.datacert.visibility= View.VISIBLE
                            binding.datacert.setOnClickListener(){
                                initActivity(Estado,Lic_func,Nombre_Razon,direccion,zona,Num_Res,Num_Exp,Giro,Area,Fecha_Exp,Fecha_Caducidad)
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
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun buscarUrl(links: String) {

            val intent =  Intent(Intent.ACTION_VIEW,Uri.parse("$links"))
            startActivity(intent)

    }
    private fun initActivity(estado:String
                             ,lic_func:String
                             ,Nombre_razon:String
                             ,Direccion:String
                             ,Zona:String
                             ,Num_Res        :String
                             ,Num_Exp        :String
                             ,Giro           :String
                             ,Area           :String
                             ,Fecha_Exp      :String
                             ,Fecha_Caducidad:String) {
        val i = Intent(this,detalleCertificados::class.java).apply {
            putExtra("Estado",estado)
            putExtra("lic_func",lic_func)
            putExtra("Nombre_razon",Nombre_razon)
            putExtra("Direccion",Direccion)
            putExtra("Zona",Zona)
            putExtra("Num_Res        ",Num_Res        )
            putExtra("Num_Exp        ",Num_Exp        )
            putExtra("Giro           ",Giro           )
            putExtra("Area           ",Area           )
            putExtra("Fecha_Exp      ",Fecha_Exp      )
            putExtra("Fecha_Caducidad",Fecha_Caducidad)



            //putExtra("",)
        }
        startActivity(i)

    }

}