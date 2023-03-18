package com.renting.app.android.feature.registration

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.android.core.uikit.RentingButton
import com.renting.app.android.core.uikit.RentingInput
import com.renting.app.android.core.uikit.input.PasswordInput
import com.renting.app.core.form.FieldForm
import com.renting.app.core.form.TextField
import com.renting.app.feature.registration.component.RegistrationComponent

@Composable
fun RegistrationScreen(component: RegistrationComponent) {
    val state by component.models.subscribeAsState()

    RegistrationScreen(
        model = state,
        onFieldChanged = component::onFieldChanged,
        onActionButtonClicked = component::completeRegistration,
        onSignInClick = component::onLoginRequired,
    )
}

@Composable
private fun RegistrationScreen(
    model: RegistrationComponent.Model,
    onFieldChanged: (id: TextField.Id, value: String) -> Unit,
    onActionButtonClicked: () -> Unit,
    onSignInClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        userScrollEnabled = false,
    ) {
        item("Title") {
            Text(
                text = "Create New Account",
                modifier = Modifier
                    .fillParentMaxWidth(),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(16.dp))
        }
        items(
            items = model.registrationForm.toList(),
            key = { field -> field.id.toString() },
        ) { field ->
            Spacer(Modifier.height(8.dp))
            val onValueChange = { value: String ->
                onFieldChanged(field.id, value)
            }
            val modifier = Modifier
                .fillParentMaxWidth()
            when (field.id.kind) {
                TextField.Kind.LOGIN -> RentingInput(
                    value = field.value,
                    modifier = modifier,
                    onValueChange = onValueChange,
                    placeholder = {
                        Text("Login")
                    },
                )
                TextField.Kind.PASSWORD -> PasswordInput(
                    password = field.value,
                    modifier = modifier,
                    onInputChanged = onValueChange,
                    error = field.error,
                )
                TextField.Kind.EMAIL -> RentingInput(
                    value = field.value,
                    modifier = modifier,
                    onValueChange = onValueChange,
                    placeholder = {
                        Text("Email")
                    },

                    )
                TextField.Kind.PHONE_NUMBER -> RentingInput(
                    value = field.value,
                    modifier = modifier,
                    onValueChange = onValueChange,
                    placeholder = {
                        Text("Phone number")
                    },
                )
                TextField.Kind.FIRST_NAME -> RentingInput(
                    value = field.value,
                    modifier = modifier,
                    onValueChange = onValueChange,
                    placeholder = {
                        Text("First name")
                    },
                )
                TextField.Kind.LAST_NAME -> RentingInput(
                    value = field.value,
                    modifier = modifier,
                    onValueChange = onValueChange,
                    placeholder = {
                        Text("Last name")
                    },
                )
            }
        }
        item("Action") {
            Spacer(Modifier.height(24.dp))
            RentingButton(
                onClick = onActionButtonClicked,
                modifier = Modifier
                    .fillParentMaxWidth(),
            ) {
                Text("Register")
            }
        }
        item("LoginInstead") {
            Spacer(Modifier.height(8.dp))
            AlreadyHaveAccount(
                onSignInClick = onSignInClick,
                modifier = Modifier
                    .fillParentMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    RentingTheme {
        Surface {
            RegistrationScreen(
                model = RegistrationComponent.Model(
                    registrationForm = FieldForm(
                        listOf(
                            TextField(TextField.Kind.LOGIN),
                            TextField(TextField.Kind.PASSWORD),
                            TextField(TextField.Kind.EMAIL),
                            TextField(TextField.Kind.PHONE_NUMBER),
                            TextField(TextField.Kind.FIRST_NAME),
                            TextField(TextField.Kind.LAST_NAME),
                        )
                    ),
                    isRegistering = false,
                ),
                onFieldChanged = { _, _ -> },
                onActionButtonClicked = {},
                onSignInClick = {},
            )
        }
    }
}
