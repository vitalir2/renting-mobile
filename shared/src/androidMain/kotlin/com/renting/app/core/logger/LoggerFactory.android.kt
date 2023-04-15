package com.renting.app.core.logger

import android.util.Log

actual fun createLogger(): Logger {
    return object : Logger {
        val tag = "Renting"

        override fun debug(msg: () -> String) {
            Log.d(tag, msg())
        }

        override fun info(msg: () -> String) {
            Log.i(tag, msg())
        }

        override fun error(msg: () -> String) {
            Log.e(tag, msg())
        }
    }
}
