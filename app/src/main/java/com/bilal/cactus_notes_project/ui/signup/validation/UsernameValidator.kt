package com.bilal.cactus_notes_project.ui.signup.validation

import com.bilal.cactus_notes_project.R

class UsernameValidator {
    fun validate(username: String) = when {
        username.isEmpty() -> R.string.username_is_required
        username.length < 3 -> R.string.username_is_too_short
        username.length > 19 -> R.string.username_is_too_long
        !username.all { it.isLowerCase() || it.isDigit() || it == '_' } -> R.string.username_restrictions
        else -> null
    }
}