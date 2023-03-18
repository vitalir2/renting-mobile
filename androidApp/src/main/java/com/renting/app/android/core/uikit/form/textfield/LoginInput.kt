package com.renting.app.android.core.uikit.form.textfield

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.renting.app.android.core.brandbook.RentingTheme

@Composable
internal fun LoginInput(
    login: String,
    modifier: Modifier = Modifier,
    onInputChanged: (String) -> Unit,
) {
    NameInput(
        value = login,
        modifier = modifier,
        onValueChange = onInputChanged,
        leadingIcon = {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = null,
            )
        },
        placeholder = "Login",
    )
}

private class LoginInputPreviewLoginProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "MyLogin",
        "",
    )
}

@Preview
@Composable
private fun LoginInputPreview(
    @PreviewParameter(LoginInputPreviewLoginProvider::class)
    login: String,
) {
    RentingTheme {
        LoginInput(
            login = login,
            onInputChanged = {},
        )
    }
}
