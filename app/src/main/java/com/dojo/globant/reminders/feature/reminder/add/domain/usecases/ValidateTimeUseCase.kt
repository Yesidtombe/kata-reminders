package com.dojo.globant.reminders.feature.reminder.add.domain.usecases

import com.dojo.globant.reminders.R
import com.dojo.globant.reminders.common.util.UiText
import com.dojo.globant.reminders.common.util.ValidationResult

class ValidateTimeUseCase {
    operator fun invoke(time: Long): ValidationResult {
        if (time == 0L) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(id = R.string.field_required_error)
            )
        }
        return ValidationResult(successful = true)
    }
}