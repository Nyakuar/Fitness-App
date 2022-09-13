package dev.nyakuar.mytrackerpal.ui

import android.content.Intent
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
import dev.Lavyne.mytrackerpal.databinding.ActivitySignUpBinding
import dev.Lavyne.mytrackerpal.models.RegisterRequest
import dev.Lavyne.mytrackerpal.models.RegisterResponse
import dev.nyakuar.mytrackerpal.models.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listener()


    }

    fun listener() {
        binding.tvLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            validateSignUp()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun validateSignUp() {
        var firstName = binding.etFirstName.text.toString()
        var lastName = binding.etLastName.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmPassword = binding.etConfirmPassword.text.toString()
        var phoneNumber = binding.etPhone.text.toString()
        var error = false

        if (email.isBlank()) {
            error = true
            binding.tilEmail.error = "Email is required"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = true
            binding.tilEmail.error = "email is invalid"
        }
        if (phoneNumber.isBlank()) {
            error = true
            binding.tilPhoneNumber.error = "phone number is required"
        }
        if (password.isBlank()) {
            error = true
            binding.tilPassword.error = "password is required"
        }
        if (confirmPassword.isBlank()) {
            error = true
            binding.tilConfirmPassword.error = "confirm password is required"
        }
        if (firstName.isBlank()) {
            error = true
            binding.tilFirstName.error = "firstName is required"
        }
        if (lastName.isBlank()) {
            error = true
            binding.tilLastName.error = "lastName is required"
        }
        if (password != confirmPassword) {
            error = true
            binding.tilConfirmPassword.error = "password invalid"
        }
        if (!error) {
            binding.pvRegister.visibility=View.VISIBLE
            var registerRequest = RegisterRequest(firstName, lastName, phoneNumber, email, password)
            userViewModel.register(registerRequest)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerLivedata.observe(this, Observer{ registerResponse->
            Toast.makeText(baseContext, registerResponse?.message, Toast.LENGTH_SHORT).show()
            startActivity(Intent(baseContext, HomeActivity::class.java))
        })
        userViewModel.registererror.observe(this, Observer{ errorMsg ->
            Toast.makeText(baseContext, errorMsg, Toast.LENGTH_SHORT).show()
        })

    }

}


