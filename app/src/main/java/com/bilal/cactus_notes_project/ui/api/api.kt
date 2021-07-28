package com.bilal.cactus_notes_project.ui.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    setLevel(Level.BODY)
}

private val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

val api = Retrofit.Builder()
    .client(client)
    .baseUrl("https://apps.cactus.school")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(NotesApi::class.java)