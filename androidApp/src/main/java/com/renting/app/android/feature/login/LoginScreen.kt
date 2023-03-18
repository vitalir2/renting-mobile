package com.renting.app.android.feature.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.renting.app.android.core.uikit.form.textfield.LoginInput
import com.renting.app.android.core.uikit.form.textfield.PasswordInput
import com.renting.app.feature.login.component.LoginComponent
import com.renting.app.core.auth.model.LoginError

@Composable
fun LoginScreen(
    component: LoginComponent,
) {
    val state by component.models.subscribeAsState()

    val context = LocalContext.current
    LaunchedEffect(key1 = state.error) {
        if (state.error is LoginError.Unknown) {
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
                .fillMaxWidth(0.3f),
            contentScale = ContentScale.Crop,
        )
        Gap(16.dp)
        Text(
            text = "Login to Your Account",
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
            error = when (model.error) {
                is LoginError.InvalidLoginOrPassword -> "Invalid login or password"
                is LoginError.Unknown,
                null,
                -> null
            },
        )
        Gap(16.dp)
        RentingButton(
            onClick = onSubmitButtonClick,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text("Sign in")
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
