package com.bilal.cactus_notes_project.ui.signup.validation

import com.bilal.cactus_notes_project.R

class EmailValidator {
    fun validate(email: String) = when {
        email.isEmpty() -> R.string.email
        email.length > 50
                || email.length < 5
                || !email.containsAtAndDot() -> R.string.email_is_invalid
        else -> null
    }

    private fun String.containsAtAndDot() = contains("@") && contains(".")
}