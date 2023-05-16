package com.mda.ateinspeccion.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.mda.ateinspeccion.R
import com.mda.ateinspeccion.databinding.ActivityScanInspectorBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.mda.ateinspeccion.adapter.CustomAdapter
import com.mda.ateinspeccion.adapter.ListCertAdapter
import com.mda.ateinspeccion.adapter.detalleAdapter1
import com.mda.ateinspeccion.detalle_inspector
import com.mda.ateinspeccion.filtrartiposlicencia
import com.mda.ateinspeccion.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class Scan_inspector : AppCompatActivity() {

    private lateinit var   binding: ActivityScanInspectorBinding
    private lateinit var datos:String
    private lateinit var  datosUser:Map<String, Objects>
    private lateinit var tolls:Toolbar
    private lateinit var  listAdapterCert: CustomAdapter
    private lateinit var listAdapter: ListCertAdapter
    //private lateinit var  adapterCert: CustomAdapter
    private lateinit var  adapterCert: detalleAdapter1
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
    public  var listcertfasociate = mutableListOf<dataCert>()



    @SuppressLint("ResourceAsColor", "RestrictedApi")
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

        //binding.datacert.visibility= View.INVISIBLE
        //binding.fecharesult1.setText("")
        //binding.constraintLayout3.setBackgroundResource(R.drawable.btn4)
        //getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        //getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        //getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.baseline_arrow_left_24)
        //getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(android.R.color.transparent)));
        tolls = findViewById(R.id.topAppBar8)
        tolls.setNavigationOnClickListener(){

            finish()

        }
        //getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.white)));
        //val display=setSupportActionBar(tolls)

         binding.btnlupa2.setOnClickListener(){
             hideKeyboard(currentFocus ?: View(this))

            val i =Intent(this,filtrartiposlicencia::class.java)
             startActivity(i)
             //initScan()
         }




        binding.btnlupa1.setOnClickListener(){
            //buscarCertificado(binding.etNumeros.text.toString())
            hideKeyboard(currentFocus ?: View(this))
            buscardatosretrofitsubgerenempresarial(
                binding.lic.text.toString(),
                binding.exp.text.toString(),
                binding.ruc.text.toString(),
                binding.nombreRazonsocial2.text.toString()
            )




        }
        adapterCert = detalleAdapter1(listcertfasociate){
                certificado ->onItemSelected(certificado)
        }
        binding.recyclerview.layoutManager= LinearLayoutManager(this)
        binding.recyclerview.adapter = adapterCert

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
        //IntentIntegrator(this).initiateScan()
    }
    fun buscardatosretrofitsubgerenempresarial(
        lic: String,
        exp: String,
        ruc: String,
        nom_razon: String
    ){
        val check=checkinternet1()
        if (check.checkForInternet(this)) {
            //Buscar certificados
            ////
            getRetrofit()
            var Lic_func1: String? =""
            var url2 = "certificados_apps/conexiones_php/FiltraNumLicencia.php?LIC=$lic"
            var url1="certificados_apps/conexiones_php/consultar.php?LIC=$lic"

            var url6=""
            var url7=""
            var url4=""
            var url3=""
            var url5=""
            // Evaluamos el estado de la variable del tipo de licencia elecciontramite

            when (elecciontramite.tipo) {
                elecciontramite.temporal-> url2="certificados_apps/conexiones_php/tiposlicencia/temporales_actualizable.php?filtro1=$lic&filtro2=$exp&filtro3=$nom_razon&filtro4=$ruc"
                elecciontramite.indeterminada -> url2="certificados_apps/conexiones_php/tiposlicencia/indeterminados_actualizable.php?filtro1=$lic&filtro2=$exp&filtro3=$nom_razon&filtro4=$ruc"
                elecciontramite.itcse -> url3="certificados_apps/conexiones_php/tiposlicencia/indeterminados_actualizable.php?filtro1=$lic&filtro2=$exp&filtro3=$nom_razon&filtro4=$ruc"
                elecciontramite.ecse -> url4="certificados_apps/conexiones_php/tiposlicencia/indeterminados_actualizable.php?filtro1=$lic&filtro2=$exp&filtro3=$nom_razon&filtro4=$ruc"
                elecciontramite.contrusccion-> url5="certificados_apps/conexiones_php/tiposlicencia/indeterminados_actualizable.php?filtro1=$lic&filtro2=$exp&filtro3=$nom_razon&filtro4=$ruc"
                elecciontramite.habilitacion -> url6="https://script.google.com/macros/s/AKfycbwaYHIekRJgODFmd_wr5q4B4MchYmJZqKG6INlnD_d3fR3cqbslQCbiboM3h64yPyvs/exec?spreadsheetId=19N8hMeopjCvvXLa3l5cjKKUyQugptAtuOokYPsWzGUw&sheet=Resoluciones-2023&expediente=76&licencia="

                else -> { // Note the block
                    url7 = "certificados_apps/conexiones_php/FiltraNumLicencia.php?LIC=$lic"

                }
            }



            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(apiService::class.java).getAllcertrelacionados(url2)
                val certificados = call.body()

                runOnUiThread {
                    if(call.isSuccessful){
                        var  listaPerros = certificados?.datos ?: emptyList()

                        listcertfasociate.clear()
                        listcertfasociate.addAll(listaPerros)
                        adapterCert.notifyDataSetChanged()
                        //objectlistcertlic.clearAllPersons()
                        objectlistcertlic.mutablelitcert=listcertfasociate
                        Log.d("ayush: ", certificados.toString())
                        //initActivity2(listcertfasociate)


                    }else{
                        showError()
                    }
                }
            }

            ////

        } else {
            Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show()
        }


        }
    private fun initActivity2(listcertfasociate: ArrayList<dataCert>) {
        val intent = Intent(this, filtroSubgerencia::class.java)
        intent.putExtra("listcertfasociate2", ArrayList(listcertfasociate))
        startActivity(intent)

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
             .baseUrl("https://proyectosti.muniate.gob.pe/")
            //.baseUrl("https://delorekbyrnison.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
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
        val i = Intent(this@Scan_inspector, list::class.java).apply {
            putExtra("Estado",estado)
            putExtra("lic_func",lic_func)
            putExtra("Nombre_razon",Nombre_razon)
            putExtra("Direccion",Direccion)
            putExtra("Zona",Zona)
            putExtra("Num_Res",Num_Res        )
            putExtra("Num_Exp",Num_Exp        )
            putExtra("Giro",Giro           )
            putExtra("Area",Area           )
            putExtra("Fecha_Exp",Fecha_Exp      )
            putExtra("Fecha_Caducidad",Fecha_Caducidad)



            //putExtra("",)
        }
        startActivity(i)

    }
    private fun showError(){
        Toast.makeText(this,"Error en la consulta listxID",Toast.LENGTH_SHORT).show()
    }


    private fun onItemSelected (datacer:dataCert){
        val i = Intent(this,list::class.java).apply {
            putExtra("Estado",datacer.Estado)
            putExtra("lic_func",datacer.Lic_Func)
            putExtra("Nombre_razon",datacer.Nombre_Razón_Social)
            putExtra("Direccion",datacer.Direccion)
            putExtra("Zona",datacer.Zona_Urbana)
            putExtra("Num_Res",datacer.Num_Res        )
            putExtra("Num_Exp",datacer.Num_Exp        )
            putExtra("Giro",datacer.Giro           )
            putExtra("Area",datacer.Area           )
            putExtra("Fecha_Exp",datacer.Fecha_Exp      )
            putExtra("Fecha_Caducidad",datacer.Fecha_Caducidad)

        }


        startActivity(i)

    }
}