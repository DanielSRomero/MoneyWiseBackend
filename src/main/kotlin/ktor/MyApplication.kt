package ktor

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.myModule() {
    configureSerialization()
    configureSecurity()
    configureDatabases()
    configureRouting()
}