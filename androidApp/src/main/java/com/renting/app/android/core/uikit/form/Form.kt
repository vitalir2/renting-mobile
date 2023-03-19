package com.renting.app.android.core.uikit.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.core.form.FieldForm
import com.renting.app.core.form.TextField

@Composable
fun Form(
    form: FieldForm,
    modifier: Modifier = Modifier,
    onFieldChanged: (id: TextField.Id, value: String) -> Unit,
    onScrollCompleted: (id: TextField.Id) -> Unit,
    scrollToField: TextField.Id? = null,
) {
    val scrollState = rememberScrollState()

    val fields = form.fields

    val focusRequesters = remember(key1 = fields.size) {
        fields.associate { it.id to FocusRequester() }
    }

    val indexes = remember(key1 = fields.size) {
        fields.withIndex().associate { it.value.id to it.index }
    }

    scrollToField?.let { fieldId ->
        LaunchedEffect(key1 = fieldId) {
            focusRequesters.getValue(fieldId).requestFocus()
            scrollState.scrollTo(indexes.getValue(fieldId))
            onScrollCompleted(fieldId)
        }
    }

    Column(
        modifier = modifier
            .verticalScroll(scrollState),
    ) {
        for ((index, field) in fields.withIndex()) {
            Spacer(Modifier.height(8.dp))
            val onValueChange = { value: String ->
                onFieldChanged(field.id, value)
            }

            val focusManager = LocalFocusManager.current
            val fieldModifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesters.getValue(field.id))

            val isLastField = index == fields.lastIndex
            val keyboardOptions = if (isLastField) {
                KeyboardOptions.Default
            } else {
                KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                )
            }

            val keyboardActions = if (isLastField) {
                KeyboardActions(
                    onDone = { focusManager.clearFocus() },
                )
            } else {
                KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                )
            }

            FormField(
                field = field,
                modifier = fieldModifier,
                onValueChange = onValueChange,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
            )
        }
    }
}

@Preview
@Composable
private fun FormPreview() {
    RentingTheme {
        Surface {
            Form(
                form = FieldForm(
                    fields = listOf(
                        TextField(TextField.Kind.LOGIN),
                        TextField(TextField.Kind.PASSWORD),
                        TextField(TextField.Kind.EMAIL),
                        TextField(TextField.Kind.PHONE_NUMBER),
                    )
                ),
                onFieldChanged = { _, _ -> },
                onScrollCompleted = {},
            )
        }
    }
}
