package ktor.routing

import domain.mapper.toUpdateUser
import domain.models.user.UpdateUser
import domain.models.user.User
import domain.usecase.ProviderUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.serialization.*
import io.ktor.server.response.*


fun Route.authRouting(){

    //Para el login
    route("/auth"){

        post(){
            try{
                val loginRequest = call.receive<UpdateUser>()
                val login : User? = ProviderUseCase.login(loginRequest.userName, loginRequest.password)

                if (login != null) {
                    val usr = login.toUpdateUser()
                    usr.token = login!!.token
                    usr.msg = "Usuario logueado correctamente"
                    call.respond(HttpStatusCode.OK, usr)
                }
                else
                    call.respond(HttpStatusCode.Unauthorized, "Problema de autenticación")

            }catch (e: Exception){
                call.respond(HttpStatusCode.BadRequest, "Formato de solicitud incorrecto")
                return@post
            }
        }

    }

    route ("/register"){

        post(){
            try{
                val user = call.receive<UpdateUser>()
                val register = ProviderUseCase.register(user)

                if (register != null) {
                    val upUsr = register.toUpdateUser()
                    upUsr.msg = "Usuario con userName =  ${upUsr.userName}, registrado correctamente. Vuelva a loguearse"
                    call.respond(HttpStatusCode.Created, upUsr)
                }
                else
                    call.respond(HttpStatusCode.Conflict, "No se ha podido realizar el registro")

            } catch (e : IllegalStateException){
                call.respond(HttpStatusCode.BadRequest, "Error en el formato de envío de datos o lectura del cuerpo.")
            } catch (e: JsonConvertException){
                call.respond(HttpStatusCode.BadRequest," Problemas en la conversión json")
            }

        }

    }

}