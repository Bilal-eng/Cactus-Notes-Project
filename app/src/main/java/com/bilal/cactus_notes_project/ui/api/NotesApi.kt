package com.bilal.cactus_notes_project.ui.api

import com.bilal.cactus_notes_project.ui.api.signup.RegisterRequest
import com.bilal.cactus_notes_project.ui.api.signup.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NotesApi {
    @POST("/auth/local/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>
}