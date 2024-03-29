package com.renting.app.android.core.uikit.form

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.renting.app.android.core.uikit.form.textfield.EmailInput
import com.renting.app.android.core.uikit.form.textfield.NameInput
import com.renting.app.android.core.uikit.form.textfield.PasswordInput
import com.renting.app.android.core.uikit.form.textfield.PhoneNumberInput
import com.renting.app.core.form.TextField

@Composable
fun FormField(
    field: TextField,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    errorModifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    when (val kind = field.id.kind) {
        TextField.Kind.LOGIN,
        TextField.Kind.FIRST_NAME,
        TextField.Kind.LAST_NAME,
        -> NameInput(
            value = field.value,
            modifier = modifier,
            onValueChange = onValueChange,
            error = field.error,
            errorModifier = errorModifier,
            placeholder = kind.namePlaceholder,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
        TextField.Kind.PASSWORD -> PasswordInput(
            password = field.value,
            modifier = modifier,
            onInputChanged = onValueChange,
            error = field.error,
            errorModifier = errorModifier,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
        TextField.Kind.EMAIL -> EmailInput(
            value = field.value,
            modifier = modifier,
            onValueChange = onValueChange,
            error = field.error,
            errorModifier = errorModifier,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
        TextField.Kind.PHONE_NUMBER -> PhoneNumberInput(
            value = field.value,
            modifier = modifier,
            onValueChange = onValueChange,
            error = field.error,
            errorModifier = errorModifier,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
    }
}

val TextField.testTag: String
    get() {
        val kind = when (id.kind) {
            TextField.Kind.LOGIN -> "login"
            TextField.Kind.PASSWORD -> "password"
            TextField.Kind.EMAIL -> "email"
            TextField.Kind.PHONE_NUMBER -> "phoneNumber"
            TextField.Kind.FIRST_NAME -> "firstName"
            TextField.Kind.LAST_NAME -> "lastName"
        }
        return "${kind}_${id.index}"
    }

val TextField.errorTestTag: String
    get() = "${testTag}_error"

private val TextField.Kind.namePlaceholder: String
    get() = when (this) {
        TextField.Kind.LOGIN -> "Login"
        TextField.Kind.FIRST_NAME -> "First name"
        TextField.Kind.LAST_NAME -> "Last name"
        else -> ""
    }
