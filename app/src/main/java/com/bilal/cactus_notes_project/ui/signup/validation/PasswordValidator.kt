package com.bilal.cactus_notes_project.ui.signup.validation

import com.bilal.cactus_notes_project.R

class PasswordValidator {
    fun validate(password: String) = when {
        password.isEmpty() -> R.string.password_is_required
        password.length < 8 -> R.string.password_is_too_short
        password.length > 39 -> R.string.password_is_too_long
        !password.containsDigit() -> R.string.password_restrictions
        !password.containsLowerCase() -> R.string.password_restrictions
        !password.containsUpperCase() -> R.string.password_restrictions
        !password.containsSpecialCharacter() -> R.string.password_restrictions
        else -> null
    }

    private fun String.containsDigit() = any { it.isDigit() }

    private fun String.containsLowerCase() = any { it.isLowerCase() }

    private fun String.containsUpperCase() = any { it.isUpperCase() }

    private fun String.containsSpecialCharacter() = any { it.isSpecialCharacter() }

    private fun Char.isSpecialCharacter() = isLetterOrDigit().not()
}