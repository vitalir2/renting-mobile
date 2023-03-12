package com.renting.app.android.core.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.brandbook.RentingTheme

@Composable
fun RentingInput(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (value: String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon:  @Composable (() -> Unit)? = null,
    trailingIcon:  @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        textStyle = MaterialTheme.typography.body1,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onSurface,
            focusedIndicatorColor = MaterialTheme.colors.primary,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}


@Composable
@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
private fun RentingInputPreview() {
    RentingTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            RentingInput(
                value = "Hello World",
                modifier = Modifier
                    .align(Alignment.Center),
                onValueChange = {},
            )
        }
    }
}