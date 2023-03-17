package com.renting.app.feature.registration

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.auth.model.InitUserData
import com.renting.app.core.auth.model.RegistrationError
import com.renting.app.core.monad.Either
import com.renting.app.core.validation.TextField
import com.renting.app.feature.registration.RegistrationStore.Intent
import com.renting.app.feature.registration.RegistrationStore.Label
import com.renting.app.feature.registration.RegistrationStore.State
import kotlinx.coroutines.launch

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
        val registrationForm = listOf(
            TextField(TextField.Kind.LOGIN),
            TextField(TextField.Kind.PASSWORD),
            TextField(TextField.Kind.EMAIL),
            TextField(TextField.Kind.PHONE_NUMBER),
            TextField(TextField.Kind.FIRST_NAME),
            TextField(TextField.Kind.LAST_NAME),
        )
        return State(
            registrationForm = registrationForm,
        )
    }

    private sealed interface Msg {
        data class FieldValue(val id: TextField.Id, val value: String) : Msg
        data class Error(val error: RegistrationError) : Msg
    }

    private class ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.FieldValue -> copy(
                registrationForm = registrationForm.updateField(msg.id) {
                    copy(value = msg.value, error = null)
                }
            )
            is Msg.Error -> when (val error = msg.error) {
                is RegistrationError.Unknown -> this
                is RegistrationError.ValidationFailed -> copy(
                    registrationForm = registrationForm.apply {
                        error.errors.forEach { (id, error) ->
                            updateField(id) { copy(error = error) }
                        }
                    },
                )
            }
        }

        companion object {
            private fun List<TextField>.updateField(
                id: TextField.Id,
                update: TextField.() -> TextField,
            ): List<TextField> = this.toMutableList().apply {
                val indexOfField = indexOfFirst { it.id === id }
                if (indexOfField > 0) {
                    val previousValue = this[indexOfField]
                    this[indexOfField] = previousValue.update()
                }
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
                        when (val result = authRepository.register(userData)) {
                            is Either.Left -> dispatch(Msg.Error(result.error))
                            is Either.Right -> publish(Label.RegistrationCompleted)
                        }
                    }
                }
                is Intent.SetFieldValue -> {
                    dispatch(Msg.FieldValue(intent.id, intent.value))
                }
            }
        }

        companion object {

            private fun List<TextField>.getValue(kind: TextField.Kind): String {
                val id = TextField.Id(kind)
                return first { it.id == id }.value
            }

            private fun List<TextField>.toUserData(): InitUserData {
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
