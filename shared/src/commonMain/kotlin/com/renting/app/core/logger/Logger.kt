package com.renting.app.core.logger

interface Logger {

    fun debug(msg: () -> String)

    fun info(msg: () -> String)

    fun error(msg: () -> String)
}

expect fun createLogger(): Logger
