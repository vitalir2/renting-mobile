package com.renting.app.android.core.uikit.form.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.renting.app.android.core.uikit.RentingInput
import com.renting.app.android.core.uikit.RentingPreviewContainer

@Composable
fun NameInput(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: String,
    error: String? = null,
    errorModifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    RentingInput(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        error = error,
        errorModifier = errorModifier,
        placeholder = {
            Text(placeholder)
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = true,
        keyboardOptions = keyboardOptions.copy(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Ascii,
        ),
        keyboardActions = keyboardActions,
    )
}

@Preview
@Composable
private fun NameInputPreview() {
    RentingPreviewContainer {
        NameInput(
            value = "Hello World",
            onValueChange = {},
            placeholder = "Hello",
        )
    }
}
