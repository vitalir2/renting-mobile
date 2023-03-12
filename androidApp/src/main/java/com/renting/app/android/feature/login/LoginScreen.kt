package com.renting.app.android.feature.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.R
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingButton
import com.renting.app.android.core.uikit.input.LoginInput
import com.renting.app.android.core.uikit.input.PasswordInput
import com.renting.app.feature.login.component.LoginComponent

@Composable
fun LoginScreen(
    component: LoginComponent,
) {
    val state by component.models.subscribeAsState()

    val context = LocalContext.current
    LaunchedEffect(key1 = state.error) {
        if (state.error != null) {
            Toast
                .makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                .show()
            component.onLoginErrorShowed()
        }
    }

    LoginScreen(
        model = state,
        onLoginInputChanged = component::onLoginChanged,
        onPasswordInputChanged = component::onPasswordChanged,
        onSubmitButtonClick = component::onLoginStarted,
        onSignUpClick = component::onRegistrationRequested,
    )
}

@Composable
private fun LoginScreen(
    model: LoginComponent.Model,
    onLoginInputChanged: (String) -> Unit,
    onPasswordInputChanged: (String) -> Unit,
    onSubmitButtonClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.renting_logo_full),
            contentDescription = "Renting logo",
            modifier = Modifier
                .fillMaxWidth(0.5f),
            contentScale = ContentScale.Crop,
        )
        Gap(16.dp)
        Text(
            text = "Login to your account",
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Bold,
            ),
            textAlign = TextAlign.Center,
        )
        Gap(32.dp)
        LoginInput(
            login = model.login,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(LoginScreenTags.LOGIN_INPUT),
            onInputChanged = onLoginInputChanged,
        )
        Gap(16.dp)
        PasswordInput(
            password = model.password,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(LoginScreenTags.PASSWORD_INPUT),
            onInputChanged = onPasswordInputChanged,
        )
        Gap(16.dp)
        RentingButton(
            onClick = onSubmitButtonClick,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text("Login")
        }
        Gap(16.dp)
        NoAccountHelp(onSignUpClick)
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xffffff,
)
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
            onSignUpClick = {},
        )
    }
}
