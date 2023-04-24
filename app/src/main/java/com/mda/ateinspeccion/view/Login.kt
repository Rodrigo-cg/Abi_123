package com.mda.ateinspeccion.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.mda.ateinspeccion.R
import com.mda.ateinspeccion.model.apiService
import com.mda.ateinspeccion.model.checkinternet1
import com.google.android.material.textfield.TextInputLayout
import com.mda.ateinspeccion.model.userInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class login : AppCompatActivity() {
    private var User=""
    private var Password=""
    private var Error=""
    private lateinit var tolls: Toolbar

    @SuppressLint("MissingInflatedId", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btningresar : Button = findViewById(R.id.btningresar)
        val txtemail : TextView = findViewById(R.id.edtEmail)
        val  txtpass : TextView = findViewById(R.id.etNumeros)

        tolls = findViewById(R.id.topapp4)
        //setSupportActionBar(tolls);
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowTitleEnabled(false);
        //getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.baseline_arrow_left_24)
        //getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(android.R.color.transparent)));
        //tolls.setBackgroundColor(android.R.color.transparent)
        tolls.setNavigationOnClickListener(){

                    finish()

        }
        //getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.white)));
        //val display=setSupportActionBar(tolls)
        btningresar.setOnClickListener()
        {
            var user1 =txtemail.text.toString()

            user1 = user1.replace("\\s".toRegex(), "")

            var password1 =txtpass.text.toString()

            password1 = password1.replace("\\s".toRegex(), "")
            validarSession(user1,password1)


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


    private fun validarSession(user:String, pass:String){
        val check= checkinternet1()

        if(check.checkForInternet(this)) {
            CoroutineScope(Dispatchers.IO).launch {
                val calls = getRetrofit().create(apiService::class.java)
                    .getValidUser("certificados_apps/conexiones_php/validarLogin.php?user=$user&pass=$pass")
                val users = calls.body()
                runOnUiThread {

                    if (calls != null) {
                        User = users?.id_user ?: "No hay user"
                        Password = users?.clave ?: "No hay contraseña"
                        Error = users?.Error ?: "No hay error"

                        if (User.equals(user) && Password.equals(pass))
                            initActivity(User)
                        else {
                            val passwordLayout: TextInputLayout = findViewById(R.id.textInputLayout)
                            passwordLayout.error = "Datos incorrectos"
                            val passwordLayout2: TextInputLayout = findViewById(R.id.textField)
                            passwordLayout2.error = "Datos incorrectos"
                        }
                    } else {

                        showToast("Error al validar usuario")

                    }
                }
            }
            // some code
        }
        else {
            Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show()
        }


    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://proyectosti.muniate.gob.pe/")
            //.baseUrl("https://delorekbyrnison.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun showToast(dato1:String){
        Toast.makeText(this,dato1,Toast.LENGTH_SHORT).show()
    }

    private fun initActivity(user:String,) {
        val i = Intent(this,tipolicencias::class.java).apply {
            putExtra("User",user)
            val passwordLayout1 =findViewById<TextInputLayout>(R.id.textInputLayout)
            passwordLayout1.error = null
            val passwordLayout: TextInputLayout =findViewById(R.id.textField)
            passwordLayout.error = null

            //putExtra("",)
            //putExtra("",)
        }
        startActivity(i)

    }
    private fun addMascotass(lista:userInfo){

        val calls = getRetrofit().create(apiService::class.java).addHistorialSesion(lista)

        calls.enqueue(
            object : Callback<postRes> {
                override fun onResponse(call: Call<postRes>, response: Response<postRes>) {
                    val res = response.body()
                    showToast("id : ${res?.id ?: "no hay id"}")
                }

                override fun onFailure(call: Call<postRes>, t: Throwable) {
                    Log.i("Error------POST", t.toString())
                }

            }
        )


    }


}