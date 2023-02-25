@file:OptIn(DelicateCoroutinesApi::class)

package com.renting.app.feature.login

import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal fun <T : Any> Store<*, T, *>.asValue(): Value<T> =
    object : Value<T>() {
        override val value: T get() = state
        private var jobs = emptyMap<(T) -> Unit, Job>()

        override fun subscribe(observer: (T) -> Unit) {
            val job = GlobalScope.launch {
                states.collect { observer(it) }
            }
            this.jobs += observer to job
        }

        override fun unsubscribe(observer: (T) -> Unit) {
            val job = jobs[observer] ?: return
            this.jobs -= observer
            job.cancel()
        }
    }
