package com.renting.app.android.core.uikit.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.android.core.uikit.RentingInput

@Composable
internal fun PasswordInput(
    password: String,
    modifier: Modifier = Modifier,
    onInputChanged: (String) -> Unit,
    error: String?,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    RentingInput(
        modifier = modifier,
        value = password,
        onValueChange = onInputChanged,
        placeholder = {
            Text("Password")
        },
        leadingIcon = leadingIcon,
        trailingIcon = {
            Icon(
                if (isPasswordVisible) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                },
                modifier = Modifier
                    .clickable {
                        isPasswordVisible = isPasswordVisible.not()
                    },
                contentDescription = null,
            )
        },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = keyboardOptions.copy(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Password,
        ),
        keyboardActions = keyboardActions,
        error = error,
    )
}

private class PasswordInputPreviewPasswordProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "Some password",
        "",
    )
}

@Preview
@Composable
private fun PasswordInputPreview(
    @PreviewParameter(PasswordInputPreviewPasswordProvider::class)
    password: String,
) {
    RentingTheme {
        PasswordInput(
            password = password,
            onInputChanged = {},
            error = null,
        )
    }
}
