package ktor

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import ktor.routing.authRouting
import ktor.routing.goalsRouting
import ktor.routing.userRouting

fun Application.configureRouting() {
    routing {

        authRouting()
        userRouting()
        goalsRouting()

        staticResources("/static", "static")
    }


}

