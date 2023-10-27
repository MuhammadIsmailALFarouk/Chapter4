package com.example.chapter4.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.chapter4.Login.Login
import com.example.chapter4.R
import com.example.chapter4.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class Register : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val username = binding.etUsername.text.toString()
            val noKontak = binding.noKontak.text


            if (username.isEmpty()){
                binding.etUsername.error = "Username tidak boleh Kosong"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()){
                binding.etEmail.error = "Email tidak boleh Kosong"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmail.error = "Email Yang Anda Masukan Salah"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.etPassword.error = "Password Harap Di isi"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6 ){
                binding.etPassword.error = "Password minimal harus 6 karakter"
                binding.etPassword.requestFocus()
                return@setOnClickListener

            }
            if (noKontak.isEmpty()){
                binding.noKontak.error = "Nomor Telephone tidak boleh Kosong"
                binding.noKontak.requestFocus()
                return@setOnClickListener
            }
            RegisterFirebase(email,password)
        }
        binding.toLogin.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }


    }
    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent (this,Login::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }

            }

    }
}
