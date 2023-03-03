package com.renting.app.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher

expect fun createIODispatcher(): CoroutineDispatcher
