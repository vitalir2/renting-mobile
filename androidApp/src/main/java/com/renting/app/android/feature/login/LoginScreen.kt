package com.renting.app.android.feature.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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

    LoginScreen(
        login = state.login,
        password = state.password,
        onLoginInputChanged = component::onLoginInputChanged,
        onPasswordInputChanged = component::onPasswordInputChanged,
    )
}

@Composable
private fun LoginScreen(
    login: String,
    password: String,
    onLoginInputChanged: (String) -> Unit,
    onPasswordInputChanged: (String) -> Unit,
) {
    Column {
        TextField(
            value = login,
            onValueChange = onLoginInputChanged,
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = onPasswordInputChanged,
        )
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    RentingTheme {
        LoginScreen(
            login = "Hello",
            password = "World",
            onLoginInputChanged = {},
            onPasswordInputChanged = {},
        )
    }
}
