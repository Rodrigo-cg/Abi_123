package com.example.appqr.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.appqr.R
import com.example.appqr.detalle_inspector
import com.example.appqr.model.apiService
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class login : AppCompatActivity() {
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private var User=""
    private var Password=""
    private var Error=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btningresar : Button = findViewById(R.id.btningresar)
        val txtemail : TextView = findViewById(R.id.edtEmail)
        val  txtpass : TextView = findViewById(R.id.edtPassword)
        firebaseAuth= Firebase.auth
        btningresar.setOnClickListener()
        {
            validarSession(txtemail.text.toString(),txtpass.text.toString())
        }





    }


    private fun validarSession(user:String, pass:String){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val calls = getRetrofit().create(apiService::class.java).getValidUser("certificados_apps/conexiones_php/validarLogin.php?user=$user&pass=$pass")
                val users = calls.body()
                runOnUiThread {

                    if(calls != null){
                        User = users?.id_user ?: "No hay user"
                        Password = users?.clave ?: "No hay contraseña"
                        Error = users?.Error ?: "No hay error"
                        showToast("$User -$Password - $user - $pass")
                        showToast("Login")
                        if(User.equals(user) && Password.equals(pass))
                            initActivity(User)
                        else
                        {
                            val passwordLayout:TextInputLayout=findViewById(R.id.textInputLayout)
                            passwordLayout.error = "Datos incorrectos"
                            val passwordLayout2:TextInputLayout=findViewById(R.id.textField)
                            passwordLayout2.error = "Datos incorrectos"
                        }
                    }else{

                        showToast("Error al validar usuario")

                    }
                }
            }
            // some code
        } catch (e: Exception) {
            showToast("Error al validar usuario 2")
            // handler
        } finally {
            // optional finally block
        }



    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://proyectosti.muniate.gob.pe/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun showToast(dato1:String){
        Toast.makeText(this,dato1,Toast.LENGTH_SHORT).show()
    }

    private fun initActivity(user:String,) {
        val i = Intent(this,Scan_inspector::class.java).apply {
            putExtra("User",user)
            //putExtra("",)
            //putExtra("",)
        }
        startActivity(i)

    }
}