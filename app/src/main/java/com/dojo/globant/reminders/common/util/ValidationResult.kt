package com.dojo.globant.reminders.common.util

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)