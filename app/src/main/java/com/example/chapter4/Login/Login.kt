package com.example.chapter4.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.chapter4.MainActivity
import com.example.chapter4.R
import com.example.chapter4.Register.Register
import com.example.chapter4.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.toRegister.setOnClickListener{
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (email.isEmpty()){
                binding.edtEmail.error = "Email tidak boleh Kosong"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmail.error = "Email Yang Anda Masukan Salah"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.edtPassword.error = "Password Harap Di isi"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }
            
            LoginFirebase(email,password)
        }
    }

    private fun LoginFirebase(email: String, password: String) {
      auth.signInWithEmailAndPassword(email,password)
          .addOnCompleteListener(this) {
              if (it.isSuccessful){
                  Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                  val intent = Intent (this,MainActivity::class.java)
                  startActivity(intent)
              }else{
                  Toast.makeText(this, "Login Gagal, Silahkan Coba Lagi", Toast.LENGTH_SHORT).show()
              }

          }




    }
}


