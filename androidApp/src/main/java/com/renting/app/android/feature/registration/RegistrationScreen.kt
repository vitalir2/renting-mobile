package com.renting.app.android.feature.registration

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.android.core.uikit.RentingButton
import com.renting.app.android.core.uikit.form.Form
import com.renting.app.android.core.uikit.form.ScrollToError
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
    var shouldScrollToError by remember { mutableStateOf(false) }

    val form = model.registrationForm
    val scrollToError =  if (shouldScrollToError && !model.isRegistering) {
        form.firstErrorField?.let {
            ScrollToError(
                id = it.id,
                onComplete = { shouldScrollToError = false },
            )
        }
    } else {
        null
    }

    Form(
        form = form,
        modifier = Modifier
            .fillMaxSize(),
        onFieldChange = onFieldChanged,
        prependedContent = {
            item("Title") {
                RegistrationScreenTitle(
                    modifier = Modifier
                        .fillParentMaxWidth(),
                )
                Spacer(Modifier.height(16.dp))
            }
        },
        appendedContent = {
            registerButtonGroup(
                onActionButtonClicked = {
                    onActionButtonClicked()
                    shouldScrollToError = true
                },
                onSignInClick = onSignInClick,
            )
        },
        scrollToError = scrollToError,
    )
}

private fun LazyListScope.registerButtonGroup(
    onActionButtonClicked: () -> Unit,
    onSignInClick: () -> Unit
) {
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

@Composable
private fun RegistrationScreenTitle(
    modifier: Modifier,
) {
    Text(
        text = "Create New Account",
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.SemiBold,
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
                ),
                onFieldChanged = { _, _ -> },
                onActionButtonClicked = {},
                onSignInClick = {},
            )
        }
    }
}
