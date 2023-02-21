package com.renting.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
