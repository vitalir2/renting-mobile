package com.renting.app.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun createIODispatcher(): CoroutineDispatcher = Dispatchers.IO
