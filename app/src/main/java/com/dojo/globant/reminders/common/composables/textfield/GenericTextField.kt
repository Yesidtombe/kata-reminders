package com.dojo.globant.reminders.common.composables.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dojo.globant.reminders.R

@Composable
fun GenericTextField(
    text: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    @StringRes label: Int? = null,
    @StringRes placeholder: Int? = null,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        MyOutlinedTextField(
            text = text,
            onValueChange = onValueChange,
            isError = errorMessage != null,
            label = label,
            placeholder = placeholder,
            isEnabled = isEnabled,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation
        )

        errorMessage?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                text = it,
                color = Color.Red
            )
        }
    }
}

@Composable
fun MyOutlinedTextField(
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    @StringRes label: Int? = null,
    @StringRes placeholder: Int? = null,
    isEnabled: Boolean,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    singleLine: Boolean = true,
    maxLines: Int = 1
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        isError = isError,
        label = {
            label?.let {
                Text(text = stringResource(id = it))
            }
        },
        placeholder = {
            placeholder?.let {
                Text(text = stringResource(id = it))
            }
        },
        enabled = isEnabled,
        shape = RoundedCornerShape(20.dp),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        maxLines = maxLines
    )
}

@Preview(name = "MyGenericTextField", showBackground = true)
@Composable
fun GenericTextFieldPreview() {
    GenericTextField(
        text = "",
        label = R.string.app_name,
        placeholder = R.string.app_name,
        onValueChange = { }
    )
}