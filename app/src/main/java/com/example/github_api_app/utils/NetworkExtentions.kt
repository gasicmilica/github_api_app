package com.example.github_api_app.utils

import com.example.github_api_app.data.response.ErrorMessage
import com.example.github_api_app.data.response.State
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.Response
import java.net.UnknownHostException

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): State<T> {
    return try {
        val response = apiCall()

        if (response.isSuccessful && response.code() in 200..299) {
            val body = response.body()
            if (body == null) {
                State.Error("Empty response body")
            } else {
                State.Success(body)
            }
        } else {
            val errorMessage = response.errorBody()?.string()

            val errorMsg = if (errorMessage.isNullOrBlank()) {
                response.message()
            } else {
                try {
                    GsonBuilder().create().fromJson(errorMessage, ErrorMessage::class.java).message
                } catch (e: JsonSyntaxException) {
                    errorMessage
                }
            }
            State.Error(errorMsg)
        }
    } catch (e: UnknownHostException) {
        State.Error("No internet connection: ${e.message}")
    } catch (e: Exception) {
        State.Error("Unexpected Error: ${e.message}")
    }
}