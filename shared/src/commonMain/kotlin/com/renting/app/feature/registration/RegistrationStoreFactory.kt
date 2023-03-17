package com.renting.app.feature.registration

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.validation.TextField
import com.renting.app.feature.registration.RegistrationStore.Intent
import com.renting.app.feature.registration.RegistrationStore.Label
import com.renting.app.feature.registration.RegistrationStore.State

internal class RegistrationStoreFactory(
    private val storeFactory: StoreFactory,
) {

    fun create(): RegistrationStore =
        object : RegistrationStore, Store<Intent, State, Label> by storeFactory.create(
            name = "RegistrationStore",
            initialState = createInitialState(),
            executorFactory = {
                ExecutorImpl()
            },
            reducer = ReducerImpl(),
        ) {}

    private fun createInitialState(): State {
        val registrationForm = listOf(
            TextField(TextField.Id.LOGIN),
            TextField(TextField.Id.PASSWORD),
            TextField(TextField.Id.EMAIL),
            TextField(TextField.Id.PHONE_NUMBER),
            TextField(TextField.Id.FIRST_NAME),
            TextField(TextField.Id.LAST_NAME),
        )
        return State(
            registrationForm = registrationForm,
        )
    }

    private sealed interface Msg {
        data class FieldValue(val id: TextField.Id, val value: String) : Msg
    }

    private class ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.FieldValue -> copy(
                registrationForm = registrationForm.updateField(msg.id) {
                    copy(value = msg.value, error = null)
                }
            )
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

    private class ExecutorImpl : CoroutineExecutor<Intent, Nothing, State, Msg, Label>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.CompleteRegistration -> {
                    // Registration request
                }
                is Intent.SetFieldValue -> {
                    dispatch(Msg.FieldValue(intent.id, intent.value))
                }
            }
        }
    }
}
