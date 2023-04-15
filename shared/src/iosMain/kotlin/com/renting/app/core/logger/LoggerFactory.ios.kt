package com.renting.app.core.logger

import platform.Foundation.NSLog

actual fun createLogger(): Logger {
    return object : Logger {
        override fun debug(msg: () -> String) {
            NSLog("Debug: ${msg()}")
        }

        override fun info(msg: () -> String) {
            NSLog("Info: ${msg()}")
        }

        override fun error(msg: () -> String) {
            NSLog("Error: ${msg()}")
        }
    }
}
