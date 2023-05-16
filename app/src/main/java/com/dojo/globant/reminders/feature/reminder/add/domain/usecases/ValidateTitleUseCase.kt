package com.dojo.globant.reminders.feature.reminder.add.domain.usecases

import com.dojo.globant.reminders.R
import com.dojo.globant.reminders.common.Constants
import com.dojo.globant.reminders.common.util.UiText
import com.dojo.globant.reminders.common.util.ValidationResult

class ValidateTitleUseCase {
    operator fun invoke(title: String): ValidationResult {
        title.trim().apply {
            if (isBlank()) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.field_required_error)
                )
            }
            if (length > Constants.MAX_CHARACTERS_NAME) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.exceeded_limit_characters_title)
                )
            }
        }
        return ValidationResult(successful = true)
    }
}