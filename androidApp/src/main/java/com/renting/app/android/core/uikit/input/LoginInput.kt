package com.renting.app.android.core.uikit.input

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.android.core.uikit.RentingInput

@Composable
internal fun LoginInput(
    login: String,
    modifier: Modifier = Modifier,
    onInputChanged: (String) -> Unit,
) {
    RentingInput(
        value = login,
        modifier = modifier,
        onValueChange = onInputChanged,
        leadingIcon = {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = null,
            )
        },
        placeholder = {
            Text("Login")
        },
        singleLine = true,
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
