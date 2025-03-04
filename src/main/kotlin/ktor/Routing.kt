package ktor

import authRouting
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktor.routing.userRouting

fun Application.configureRouting() {
    routing {

        authRouting()
        userRouting()

        staticResources("/static", "static")
    }


}

