package ktor

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.myModule() {
    configureContext(this)
    configureSerialization()
    configureSecurity()
    configureDatabases()
    configureRouting()
}