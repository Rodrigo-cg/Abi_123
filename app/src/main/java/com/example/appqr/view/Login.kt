package com.example.appqr.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.appqr.R
import com.example.appqr.detalle_inspector
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btningresar : Button = findViewById(R.id.btningresar)
        val btnListado : Button = findViewById(R.id.btnListado)
        val txtemail : TextView = findViewById(R.id.edtEmail)
        val  txtpass : TextView = findViewById(R.id.edtPassword)
        firebaseAuth= Firebase.auth
        btningresar.setOnClickListener()
        {
              signIn(txtemail.text.toString(),txtpass.text.toString())
        }

        btnListado.setOnClickListener(){

            val intento = Intent(this, detalle_inspector::class.java)
            startActivity(intento)
        }


    }
    private fun signIn(email: String, password: String)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext,"Bienvenido...", Toast.LENGTH_SHORT).show()
                //Aqui vamos a ir a la otra pollada
                val i = Intent(this, Scan_inspector::class.java)
                startActivity(i)
            }
            else
                Toast.makeText(baseContext,"Error de Email y/o contraseña", Toast.LENGTH_SHORT).show()
        }

    }
}