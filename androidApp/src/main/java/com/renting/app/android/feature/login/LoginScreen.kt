package com.renting.app.android.feature.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.R
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.android.core.uikit.RentingButton
import com.renting.app.android.core.uikit.RentingInput
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

private const val SIGN_UP_TAG = "SIGNUP"

@OptIn(ExperimentalTextApi::class)
@Composable
private fun LoginScreen(
    model: LoginComponent.Model,
    onLoginInputChanged: (String) -> Unit,
    onPasswordInputChanged: (String) -> Unit,
    onSubmitButtonClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    val primaryColor = MaterialTheme.colors.primary
    val signUpText = buildAnnotatedString {
        append("Don't have an account? ")
        withAnnotation(tag = SIGN_UP_TAG, annotation = "") {
            withStyle(
                SpanStyle(
                    color = primaryColor,
                    fontWeight = FontWeight.SemiBold,
                )
            ) {
                append("Sign up")
            }
        }
    }

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
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Login to your account",
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Bold,
            ),
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(32.dp))
        RentingInput(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(LoginScreenTags.LOGIN_INPUT),
            value = model.login,
            onValueChange = onLoginInputChanged,
            leadingIcon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = null,
                )
            },
            singleLine = true,
        )
        Spacer(Modifier.height(16.dp))
        PasswordInput(
            password = model.password,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(LoginScreenTags.PASSWORD_INPUT),
            onInputChanged = onPasswordInputChanged,
        )
        Spacer(Modifier.height(16.dp))
        RentingButton(
            onClick = onSubmitButtonClick,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text("Login")
        }
        Spacer(Modifier.height(16.dp))
        ClickableText(
            text = signUpText,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onBackground,
            ),
            onClick = { offset ->
                signUpText.getStringAnnotations(SIGN_UP_TAG, offset, offset)
                    .firstOrNull()
                    ?.let { onSignUpClick() }
            },
        )
    }
}

@Composable
private fun PasswordInput(
    password: String,
    modifier: Modifier = Modifier,
    onInputChanged: (String) -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    RentingInput(
        modifier = modifier,
        value = password,
        onValueChange = onInputChanged,
        leadingIcon = {
            Icon(
                Icons.Default.Lock,
                contentDescription = null,
            )
        },
        trailingIcon = {
            Icon(
                if (isPasswordVisible) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                },
                modifier = Modifier
                    .clickable {
                        isPasswordVisible = isPasswordVisible.not()
                    },
                contentDescription = null,
            )
        },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
    )
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
