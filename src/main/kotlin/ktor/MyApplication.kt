package com.example.ktor

import io.ktor.server.application.*
import ktor.configureDatabases
import ktor.configureRouting
import ktor.configureSecurity

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.myModule() {
    configureSerialization()
    configureSecurity()
    configureDatabases()
    configureRouting()
}
