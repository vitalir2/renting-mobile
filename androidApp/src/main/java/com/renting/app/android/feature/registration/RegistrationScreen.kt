package com.renting.app.android.feature.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.renting.app.android.core.uikit.form.Form
import com.renting.app.core.form.FieldForm
import com.renting.app.core.form.TextField
import com.renting.app.feature.registration.component.RegistrationComponent

@Composable
fun RegistrationScreen(component: RegistrationComponent) {
    val model by component.models.subscribeAsState()

    RegistrationScreen(
        model = model,
        onFieldChanged = component::onFieldChanged,
        onActionButtonClicked = component::completeRegistration,
        onSignInClick = component::onLoginRequired,
        onScrollToErrorCompleted = component::onScrollToErrorCompleted,
    )
}

@Composable
private fun RegistrationScreen(
    model: RegistrationComponent.Model,
    onFieldChanged: (id: TextField.Id, value: String) -> Unit,
    onActionButtonClicked: () -> Unit,
    onSignInClick: () -> Unit,
    onScrollToErrorCompleted: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        RegistrationScreenTitle(
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))
        Form(
            form = model.registrationForm,
            modifier = Modifier
                .fillMaxWidth(),
            onFieldChanged = onFieldChanged,
            onScrollCompleted = {
                onScrollToErrorCompleted()
            },
            scrollToField = model.scrollToErrorFieldId.takeIf {
                it != null && !model.isRegistering
            },
        )
        RegisterButtonGroup(
            isButtonLoading = model.isRegistering,
            onActionButtonClicked = onActionButtonClicked,
            onSignInClick = onSignInClick,
        )
    }
}

@Composable
private fun RegisterButtonGroup(
    isButtonLoading: Boolean,
    onActionButtonClicked: () -> Unit,
    onSignInClick: () -> Unit
) {
    Column {
        Spacer(Modifier.height(24.dp))
        RentingButton(
            onClick = onActionButtonClicked,
            modifier = Modifier
                .fillMaxWidth(),
            isLoading = isButtonLoading,
        ) {
            Text("Register")
        }
        Spacer(Modifier.height(8.dp))
        AlreadyHaveAccount(
            onSignInClick = onSignInClick,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Composable
private fun RegistrationScreenTitle(
    modifier: Modifier,
) {
    Text(
        text = "Create New Account",
        modifier = modifier,
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
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
                    scrollToErrorFieldId = null,
                ),
                onFieldChanged = { _, _ -> },
                onActionButtonClicked = {},
                onSignInClick = {},
                onScrollToErrorCompleted = {},
            )
        }
    }
}
