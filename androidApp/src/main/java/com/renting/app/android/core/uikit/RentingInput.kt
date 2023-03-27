package com.renting.app.android.core.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Deck
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.brandbook.RentingTheme

@Composable
fun RentingInput(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (value: String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    error: String? = null,
    errorModifier: Modifier = Modifier,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    Column {
        OutlinedTextField(
            value = value,
            modifier = modifier,
            onValueChange = onValueChange,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            isError = error != null,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            textStyle = MaterialTheme.typography.body1,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.onSurface,
                focusedIndicatorColor = MaterialTheme.colors.primary,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        )
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = errorModifier
                    .padding(start = 16.dp),
            )
        }
    }
}

private data class RentingInputPreviewData(
    val value: String,
    val singleLine: Boolean = false,
    val placeholder: @Composable (() -> Unit)? = null,
    val leadingIcon: @Composable (() -> Unit)? = null,
    val trailingIcon: @Composable (() -> Unit)? = null,
    val errorMessage: String? = null,
)

private class RentingInputPreviewProvider : PreviewParameterProvider<RentingInputPreviewData> {
    private val longString = "HelloWorld".repeat(15)

    override val values: Sequence<RentingInputPreviewData> = sequenceOf(
        RentingInputPreviewData(
            value = "Hello world",
        ),
        RentingInputPreviewData(
            value = "",
        ),
        RentingInputPreviewData(
            value = longString,
        ),
        RentingInputPreviewData(
            value = longString,
            singleLine = true,
        ),
        RentingInputPreviewData(
            value = "",
            placeholder = { Text("EnterSomething") },
        ),
        RentingInputPreviewData(
            value = "SomeValue",
            trailingIcon = { Icon(Icons.Default.AccessTime, null) },
        ),
        RentingInputPreviewData(
            value = "Another value",
            leadingIcon = { Icon(Icons.Default.Deck, null) },
        ),
        RentingInputPreviewData(
            value = "NotEmpty",
            leadingIcon = { Icon(Icons.Default.Accessibility, null) },
            trailingIcon = { Icon(Icons.Default.OpenInBrowser, null) },
        ),
        RentingInputPreviewData(
            value = "Error value",
            errorMessage = "Invalid input data",
        ),
    )
}

@Composable
@Preview(
    name = "Default",
)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Small width",
    widthDp = 200,
)
private fun RentingInputPreview(
    @PreviewParameter(RentingInputPreviewProvider::class)
    data: RentingInputPreviewData,
) {
    RentingTheme {
        RentingInput(
            value = data.value,
            onValueChange = {},
            placeholder = data.placeholder,
            leadingIcon = data.leadingIcon,
            trailingIcon = data.trailingIcon,
            singleLine = data.singleLine,
            error = data.errorMessage,
        )
    }
}
