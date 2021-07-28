package com.bilal.cactus_notes_project.ui.user

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit

class UserStore(private val context: Context) {
    private val prefs get() = context.getSharedPreferences("user_store", MODE_PRIVATE)

    fun saveJwt(jwt: String) {
        prefs.edit(commit = true) {
            putString(KEY_JWT, jwt)
        }
    }

    companion object {
        const val KEY_JWT = "jwt"
    }
}