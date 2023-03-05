package com.renting.app.android.feature.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.feature.login.LoginComponent

@Composable
fun LoginScreen(
    component: LoginComponent,
) {
    val state by component.models.subscribeAsState()

    when {
        state.error != null -> ErrorPlaceholder(state)
        state.token.isEmpty() -> LoginScreen(
            model = state,
            onLoginInputChanged = component::onLoginInputChanged,
            onPasswordInputChanged = component::onPasswordInputChanged,
            onSubmitButtonClick = component::onLoginStarted,
        )
        else -> SuccessTokenScreen(state)
    }
}

@Composable
private fun SuccessTokenScreen(state: LoginComponent.Model) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(state.token, Modifier.align(Alignment.Center))
    }
}

@Composable
private fun ErrorPlaceholder(state: LoginComponent.Model) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Error ${state.error}",
        )
    }
}

@Composable
private fun LoginScreen(
    model: LoginComponent.Model,
    onLoginInputChanged: (String) -> Unit,
    onPasswordInputChanged: (String) -> Unit,
    onSubmitButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TextField(
            modifier = Modifier
                .testTag(LoginScreenTags.LOGIN_INPUT),
            value = model.login,
            onValueChange = onLoginInputChanged,
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier
                .testTag(LoginScreenTags.PASSWORD_INPUT),
            value = model.password,
            onValueChange = onPasswordInputChanged,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onSubmitButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Text("Login")
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    RentingTheme {
        LoginScreen(
            model = LoginComponent.Model(
                login = "Hello",
                password = "World",
            ),
            onLoginInputChanged = {},
            onPasswordInputChanged = {},
            onSubmitButtonClick = {},
        )
    }
}
