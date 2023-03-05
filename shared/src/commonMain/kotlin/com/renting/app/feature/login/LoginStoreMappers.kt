package com.renting.app.feature.login

internal val stateToModel: LoginStore.State.() -> LoginComponent.Model = {
    LoginComponent.Model(
        login = login,
        password = password,
        token = token,
        error = error,
    )
}
