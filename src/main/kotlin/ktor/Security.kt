package ktor

import domain.security.JwtConfig
import domain.usecase.ProviderUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSecurity(){

    install(Authentication ){
        jwt("jwt-auth") {
            JwtConfig.configureAuthentication(this)
        }
    }

    routing {
        authenticate("jwt-auth") {


            get("/protected") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal?.getClaim("username", String::class)
                call.respondText("Hello, $username! You are authenticated.")
            }
        }
    }
}

suspend fun ApplicationCall.validateToken(token: String): Boolean{
    val dataUser = this.principal<JWTPrincipal>()
    val userName = dataUser?.payload?.getClaim("userName")?.asString()

    val user = ProviderUseCase.getUserByUserName(userName!!)
    if (user == null || token != user.token){
        //El usuario que hay en el token, no existe en la BBDD
        this.respond(HttpStatusCode.Unauthorized, "Token inválido o usuario No disponible")
        return false //El token no coincide con el de la BBDD.
    }else
        return true  //El token es el mismo que el del usuario


}