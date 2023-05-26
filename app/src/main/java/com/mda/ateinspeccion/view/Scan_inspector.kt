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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mda.ateinspeccion.R
import com.mda.ateinspeccion.databinding.ActivityScanInspectorBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.mda.ateinspeccion.adapter.CustomAdapter
import com.mda.ateinspeccion.adapter.ListCertAdapter
import com.mda.ateinspeccion.adapter.detalleAdapter1
import com.mda.ateinspeccion.filtrartiposlicencia
import com.mda.ateinspeccion.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    public  var listcertfasociateTemp = mutableListOf<ResponseDataSQL_ind_temp>()



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
                binding.expLic.text.toString(),
                binding.expITCSE.text.toString(),
                binding.expSghue.text.toString(),
                binding.ruc.text.toString(),
                binding.nombreRazonsocial2.text.toString()
            )




        }
        adapterCert = detalleAdapter1(listcertfasociateTemp){
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
        exp_lic: String,
        exp_sgrd: String,
        exp_sghue:String,
        ruc: String,
        nom_razon: String
    ){
        val check=checkinternet1()
        if (check.checkForInternet(this)) {
            //Buscar certificados
            ////

            ///Configurar Retrofit para las APIS

            ////FORMALIZACION EMPRESARIAL
            val Retrofit_mysql_ind = Retrofit.Builder()
                .baseUrl("https://proyectosti.muniate.gob.pe")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Retro_forma_indt_apiservice = Retrofit_mysql_ind.create(IapiService::class.java)

            val Retrofit_mysql_temp = Retrofit.Builder()
                .baseUrl("https://proyectosti.muniate.gob.pe")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Retro_forma_temp_apiservice = Retrofit_mysql_temp.create(IapiService::class.java)

            ////HABILITACIONES
            val Retrofit_google_SGHUE = Retrofit.Builder()
                .baseUrl("https://script.google.com/macros/s/AKfycby_NGahQB_zV7CzcFACxJQRi063uAdU7wAktnMAYv2FI_XrY5h-l8AI_iLqur4jyL5o/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val Retro_hab_apiservice = Retrofit_google_SGHUE.create(IapiService::class.java)

            ////RIESGO DESASTRE
            val Retrofit_google_riesgo_desastre_2023 = Retrofit.Builder()
                .baseUrl("https://script.google.com/macros/s/AKfycby_NGahQB_zV7CzcFACxJQRi063uAdU7wAktnMAYv2FI_XrY5h-l8AI_iLqur4jyL5o/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val Retro_Riesgo2023_apiservice = Retrofit_google_riesgo_desastre_2023.create(IapiService::class.java)


            val Retrofit_MySQL_riesgo_desastre_2022 = Retrofit.Builder()
                .baseUrl("https://kbmpqkhuwqrwegdkwqqm.supabase.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Retro_Riesgo2022_apiservice = Retrofit_MySQL_riesgo_desastre_2022.create(IapiService::class.java)

            ///Terminar de configurar Retrofit

            var Lic_func1: String? =""
            var url_1 = ""
            var url_2=""

            var url6=""
            var url7=""
            var url4=""
            var url3=""
            var url5=""
            // Evaluamos el estado de la variable del tipo de licencia elecciontramite

            val spreadsheetId = "19N8hMeopjCvvXLa3l5cjKKUyQugptAtuOokYPsWzGUw"
            val sheet = "Resoluciones-2023"
            val razonNombre = " "

            url_1="certificados_apps/conexiones_php/tiposlicencia/temporales_actualizable.php?filtro1=$lic&filtro2=$exp_lic&filtro3=$nom_razon&filtro4=$ruc"
            url_2="certificados_apps/conexiones_php/tiposlicencia/indeterminados_actualizable.php?filtro1=$lic&filtro2=$exp_lic&filtro3=$nom_razon&filtro4=$ruc"


            lifecycleScope.launch(Dispatchers.IO) {

                val sqlIndt_deferred =async { Retro_forma_indt_apiservice.getAllLicInd(
                    "exec?spreadsheetId=19N8hMeopjCvvXLa3l5cjKKUyQugptAtuOokYPsWzGUw&sheet=Resoluciones-2023&expediente=70&razon_nombre="
                ) }

                val sqlTemp_deferred = async{Retro_forma_temp_apiservice.getAllLicTemp(
                    "certificados_apps/conexiones_php/tiposlicencia/temporales_actualizable.php?filtro1=589&filtro2=&filtro3=&filtro4="
                )}

                val google_SGHUE_deferred = async{Retro_hab_apiservice.getAllHabilitacion(spreadsheetId, sheet, exp_sghue, nom_razon)}

                val google_RiesgoDesastre_deferred = async{Retro_Riesgo2023_apiservice.getAllITCSE_ECSE_2023(spreadsheetId, sheet, exp_sghue, nom_razon)}

                val google_RiesgoDesastre2022_deferred  = async{Retro_Riesgo2022_apiservice.getAllITCSE_ECSE_2022(
                    "rest/v1/TABLE_2022_RIESGO?select=*"
                )}


                val sqlInd_data=sqlIndt_deferred.await()
                val sqlTemp_data=sqlTemp_deferred.await()
                val googleSGHUE_data=google_SGHUE_deferred.await()
                val googleRiesgoDesastre=google_RiesgoDesastre_deferred.await()
                val googleRiesgoDesastre2022=google_RiesgoDesastre2022_deferred.await()





                // Procesar los datos de respuesta según sea necesario
                withContext(Dispatchers.Main){
                    if(sqlInd_data.isSuccessful){
                        val cuerpo_sqlInd_data=sqlInd_data.body()

                        var  listaTemp = cuerpo_sqlInd_data?.datos ?: emptyList()

                        listcertfasociateTemp.clear()
                        listcertfasociateTemp.addAll(listaTemp)
                        adapterCert.notifyDataSetChanged()
                        //objectlistcertlic.clearAllPersons()
                        objectlistcertlic.mutablelitcert=listcertfasociateTemp


                        Toast.makeText(this@Scan_inspector,"chapar api 1 google",Toast.LENGTH_SHORT).show()
                        Log.d("ayush google sheets: ", cuerpo_sqlInd_data.toString())

                    }
                    if(sqlTemp_data.isSuccessful){
                        val cuerpo_sqlTemp_data=sqlTemp_data.body()

                        Toast.makeText(this@Scan_inspector,"chapar api 2 sql",Toast.LENGTH_SHORT).show()
                        Log.d("ayush sql: ", cuerpo_sqlTemp_data.toString())

                    }
                    if(googleSGHUE_data.isSuccessful){
                        val cuerpo_googleSGHUE_data=googleSGHUE_data.body()

                        Toast.makeText(this@Scan_inspector,"chapar api 2 sql",Toast.LENGTH_SHORT).show()
                        Log.d("ayush sql: ", cuerpo_googleSGHUE_data.toString())

                    }
                    if(googleRiesgoDesastre.isSuccessful){
                        val cuerpo_googleRiesgoDesastre=googleRiesgoDesastre.body()

                        Toast.makeText(this@Scan_inspector,"chapar api 2 sql",Toast.LENGTH_SHORT).show()
                        Log.d("ayush sql: ", cuerpo_googleRiesgoDesastre.toString())

                    }
                    if(googleRiesgoDesastre2022.isSuccessful){
                        val cuerpo_googleRiesgoDesastre2022=googleRiesgoDesastre2022.body()

                        Toast.makeText(this@Scan_inspector,"chapar api 2 sql",Toast.LENGTH_SHORT).show()
                        Log.d("ayush sql: ", cuerpo_googleRiesgoDesastre2022.toString())

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