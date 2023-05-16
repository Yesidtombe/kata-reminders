package com.dojo.globant.reminders.feature.reminder.add.domain.usecases

import com.dojo.globant.reminders.R
import com.dojo.globant.reminders.common.Constants
import com.dojo.globant.reminders.common.util.UiText
import com.dojo.globant.reminders.common.util.ValidationResult

class ValidateDescriptionUseCase {
    operator fun invoke(description: String): ValidationResult {
        description.trim().apply {
            if (isBlank()) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.field_required_error)
                )
            }
            if (length > Constants.MAX_CHARACTERS_DESCRIPTION) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.exceeded_limit_characters_description)
                )
            }
        }
        return ValidationResult(successful = true)
    }
}