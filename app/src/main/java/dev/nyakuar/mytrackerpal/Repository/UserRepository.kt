package dev.Lavyne.mytrackerpal.Repository

import dev.Lavyne.mytrackerpal.API.ApiClient
import dev.Lavyne.mytrackerpal.API.ApiInterface
import dev.Lavyne.mytrackerpal.models.LoginRequest
import dev.Lavyne.mytrackerpal.models.LoginResponse
import dev.Lavyne.mytrackerpal.models.RegisterRequest
import dev.Lavyne.mytrackerpal.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient=ApiClient.buldApiClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: LoginRequest):Response<LoginResponse>
    = withContext(Dispatchers.IO){
        val response=apiClient.loginUser(loginRequest)
        return@withContext response
    }
    suspend fun registerUser(registerRequest: RegisterRequest):Response<RegisterResponse>
    =   withContext(Dispatchers.IO){
        val response =apiClient.registerUser(registerRequest)
        return@withContext response

    }


}