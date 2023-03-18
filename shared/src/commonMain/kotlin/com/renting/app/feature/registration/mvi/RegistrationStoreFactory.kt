package com.renting.app.feature.registration.mvi

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.auth.model.InitUserData
import com.renting.app.core.monad.Either
import com.renting.app.core.form.FieldForm
import com.renting.app.core.form.TextField
import com.renting.app.feature.registration.di.RegistrationGraph
import com.renting.app.feature.registration.mvi.RegistrationStore.Intent
import com.renting.app.feature.registration.mvi.RegistrationStore.Label
import com.renting.app.feature.registration.mvi.RegistrationStore.State
import kotlinx.coroutines.launch
import com.renting.app.core.auth.model.RegistrationError as DomainRegistrationError

internal class RegistrationStoreFactory(
    private val storeFactory: StoreFactory,
    private val registrationGraph: RegistrationGraph,
) {

    fun create(): RegistrationStore =
        object : RegistrationStore, Store<Intent, State, Label> by storeFactory.create(
            name = "RegistrationStore",
            initialState = createInitialState(),
            executorFactory = {
                ExecutorImpl(
                    registrationGraph = registrationGraph,
                )
            },
            reducer = ReducerImpl(),
        ) {}

    private fun createInitialState(): State {
        val registrationFormFields = listOf(
            TextField(TextField.Kind.LOGIN),
            TextField(TextField.Kind.PASSWORD),
            TextField(TextField.Kind.EMAIL),
            TextField(TextField.Kind.PHONE_NUMBER),
            TextField(TextField.Kind.FIRST_NAME),
            TextField(TextField.Kind.LAST_NAME),
        )
        return State(
            registrationForm = FieldForm(registrationFormFields),
        )
    }

    private sealed interface Msg {
        object RegistrationStarted : Msg
        object RegistrationFinished : Msg
        data class FieldValue(val id: TextField.Id, val value: String) : Msg
        data class RegistrationError(val error: DomainRegistrationError) : Msg
    }

    private class ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.FieldValue -> copy(
                registrationForm = registrationForm.updateField(msg.id) {
                    copy(value = msg.value, error = null)
                }
            )
            is Msg.RegistrationStarted -> copy(
                isRegistering = true,
            )
            is Msg.RegistrationFinished -> copy(
                isRegistering = false,
            )
            is Msg.RegistrationError -> when (val error = msg.error) {
                is DomainRegistrationError.Unknown -> copy(
                    isRegistering = false,
                )
                is DomainRegistrationError.ValidationFailed -> copy(
                    registrationForm = registrationForm.applyErrors(error.errors),
                    isRegistering = false,
                )
            }
        }
    }

    private class ExecutorImpl(
        registrationGraph: RegistrationGraph,
    ) : RegistrationGraph by registrationGraph,
        CoroutineExecutor<Intent, Nothing, State, Msg, Label>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.CompleteRegistration -> {
                    scope.launch {
                        val state = getState()
                        val userData = state.registrationForm.toUserData()
                        dispatch(Msg.RegistrationStarted)
                        when (val result = authRepository.register(userData)) {
                            is Either.Left -> dispatch(Msg.RegistrationError(result.error))
                            is Either.Right -> {
                                dispatch(Msg.RegistrationFinished)
                                publish(Label.RegistrationCompleted)
                            }
                        }
                    }
                }
                is Intent.SetFieldValue -> {
                    dispatch(Msg.FieldValue(intent.id, intent.value))
                }
            }
        }

        companion object {
            private fun FieldForm.toUserData(): InitUserData {
                return InitUserData(
                    credentials = InitUserData.Credentials(
                        login = getValue(TextField.Kind.LOGIN),
                        password = getValue(TextField.Kind.PASSWORD),
                    ),
                    email = getValue(TextField.Kind.EMAIL),
                    phoneNumber = getValue(TextField.Kind.PHONE_NUMBER),
                    fullName = InitUserData.FullName(
                        firstName = getValue(TextField.Kind.FIRST_NAME),
                        lastName = getValue(TextField.Kind.LAST_NAME),
                        patronymic = null, // TODO add later in the app and to the backend or remove completely
                    ),
                )
            }
        }
    }
}
