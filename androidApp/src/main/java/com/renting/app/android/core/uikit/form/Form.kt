package com.renting.app.android.core.uikit.form

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
    onFieldChange: (id: TextField.Id, value: String) -> Unit,
    prependedContent:  (LazyListScope.() -> Unit)? = null,
    appendedContent:  (LazyListScope.() -> Unit)? = null,
    scrollToError: ScrollToError? = null,
) {
    val fields = form.toList()

    val focusRequesters = remember(key1 = fields.size) {
        fields.associate { it.id to FocusRequester() }
    }

    scrollToError?.let { scroll ->
        LaunchedEffect(key1 = scroll) {
            focusRequesters.getValue(scroll.id).requestFocus()
            scrollToError.onComplete(scroll.id)
        }
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
    ) {
        if (prependedContent != null) prependedContent()

        itemsIndexed(fields) { index, field ->
            Spacer(Modifier.height(8.dp))
            val onValueChange = { value: String ->
                onFieldChange(field.id, value)
            }

            val focusManager = LocalFocusManager.current
            val fieldModifier = Modifier
                .fillParentMaxWidth()
                .focusRequester(focusRequesters.getValue(field.id))

            val keyboardOptions = if (index < fields.lastIndex) {
                KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                )
            } else {
                KeyboardOptions.Default
            }

            val keyboardActions = if (index < fields.lastIndex) {
                KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                )
            } else {
                KeyboardActions(
                    onDone = { focusManager.clearFocus() },
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

        if (appendedContent != null) appendedContent()
    }
}

class ScrollToError(
    val id: TextField.Id,
    val onComplete: (id: TextField.Id) -> Unit,
)

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
                    )
                ),
                onFieldChange = { _, _ -> },
            )
        }
    }
}
