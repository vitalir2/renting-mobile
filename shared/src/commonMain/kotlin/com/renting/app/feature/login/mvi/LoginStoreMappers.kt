package com.renting.app.feature.login.mvi

import com.renting.app.feature.login.component.LoginComponent

internal val stateToModel: LoginStore.State.() -> LoginComponent.Model = {
    LoginComponent.Model(
        login = login,
        password = password,
        error = error,
    )
}
