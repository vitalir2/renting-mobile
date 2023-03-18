package com.renting.app.android.core.uikit.form.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.renting.app.android.core.uikit.RentingInput

@Composable
fun PhoneNumberInput(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (value: String) -> Unit,
    error: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    RentingInput(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        error = error,
        placeholder = {
            Text("Phone number")
        },
        singleLine = true,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Phone,
        ),
        keyboardActions = keyboardActions,
    )
}
