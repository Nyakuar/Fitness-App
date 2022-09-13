package dev.nyakuar.mytrackerpal.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.Lavyne.mytrackerpal.API.ApiClient
import dev.Lavyne.mytrackerpal.API.ApiInterface
import dev.Lavyne.mytrackerpal.ViewModel.UserViewModel
import dev.Lavyne.mytrackerpal.databinding.ActivityLoginBinding
import dev.Lavyne.mytrackerpal.models.LoginRequest
import dev.Lavyne.mytrackerpal.models.LoginResponse
import dev.nyakuar.mytrackerpal.models.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        setContentView(binding.root)
        handleListener()

    }
    fun handleListener(){
        binding.tvSignUp.setOnClickListener {
            val intent=Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogIn.setOnClickListener {
            validateLogin()
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginLivedata.observe(this, Observer{ loginResponse ->
            Toast.makeText(baseContext,loginResponse?.message, Toast.LENGTH_SHORT).show()
            persistLoginDetails(loginResponse!!)
            startActivity(Intent(baseContext,HomeActivity::class.java))
        })
        userViewModel.loginerror.observe(this, Observer { errorMsg ->
            Toast.makeText(baseContext,errorMsg,Toast.LENGTH_LONG).show()
        })
    }
    fun validateLogin(){
        var email =binding.etEmail.text.toString()
        var password =binding.etPassword.text.toString()
        var error =false

        if  (email.isBlank()){
            error=true
            binding.tilEmail.error ="Email is required"
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            error =true
            binding.tilEmail.error="Email is invalid"
        }

        if  (password.isBlank()){
            error=true
            binding.tilPassword.error ="password is required"
        }
        if (!error){
            binding.pvLogin.visibility=View.VISIBLE
            val loginRequest=LoginRequest(email, password)
            userViewModel.login(loginRequest)

        }
    }

    fun persistLoginDetails(loginResponse: LoginRequest){
        val editor=sharedPrefs.edit()
        editor.putString("USER_ID",loginResponse.user)
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()
    }

}