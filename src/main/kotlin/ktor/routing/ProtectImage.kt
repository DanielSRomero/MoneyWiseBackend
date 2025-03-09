package ktor.routing

import ktor.ApplicationContext
import ktor.validateToken
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.imgRouting(){
    route("/images/{userName}/{image}") {
        authenticate("jwt-auth") {

            get() {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") //token el header
                val validate = call.validateToken(token!!)  //si llega aqúi, es porque el token se ha verificado antes automaticamente
                if (!validate)
                    return@get  //Ya se ha mandado el responde dentro de la validación

                val userName = call.parameters["userName"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Necesitamos el nombre de usuario")
                val nameImage = call.parameters["image"] ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Necesitamos la imagen"
                )
                //Necesito comprobar si existe el fichero y en su caso, devolverlo.
                val path = ApplicationContext.context.environment.config.property("ktor.path.images").getString() + "/$userName"
                val img = File(path, nameImage)  //Ya tengo la imagen
                if (!img.exists()){
                    return@get call.respond(HttpStatusCode.BadRequest, "Imagen no encontrada")
                }
                /*
                La imagen existe y por tanto,
                tengo que devolverle la url de dicha imagen.
                 */
                call.respondFile(img)  //mandamos la imagen completa.

            }
        }
    }
}