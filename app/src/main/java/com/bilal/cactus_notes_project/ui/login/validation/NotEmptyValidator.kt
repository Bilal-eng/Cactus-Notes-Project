package com.bilal.cactus_notes_project.ui.login.validation

abstract class NotEmptyValidator(private val message: Int) {

    fun validate(value: String) = when {
        value.isEmpty() -> message
        else -> null
    }
}