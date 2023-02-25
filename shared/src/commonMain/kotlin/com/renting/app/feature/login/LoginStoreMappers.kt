package com.renting.app.feature.login

internal val stateToModel: LoginStore.State.() -> LoginScreen.Model = {
    LoginScreen.Model(
        login = login,
        password = password,
    )
}

internal val eventToIntent: LoginScreen.Event.() -> LoginStore.Intent = {
    when (this) {
        is LoginScreen.Event.LoginInputChanged -> LoginStore.Intent.SetLogin(value)
        is LoginScreen.Event.PasswordInputChanged -> LoginStore.Intent.SetPassword(value)
    }
}
