package ktor.routing

import data.security.PasswordHash
import domain.mapper.toUpdateUser
import domain.models.user.UpdateUser
import domain.models.user.User
import domain.usecase.ProviderUseCase
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.*
import io.ktor.server.request.*
import ktor.validateToken

fun Route.userRouting(){

    route ("/"){
        get() {
            call.respondText("Hello World!")
        }
    }


    route ("/user"){

        authenticate("jwt-auth"){
            get(){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validate = call.validateToken(token!!)
                if (!validate)
                    return@get

                val userUserName = call.request.queryParameters["userName"]
                ProviderUseCase.logger.warn("El nombre de usuario tiene de valor $userUserName")
                if (userUserName != null) {
                    val user = ProviderUseCase.getUserByUserName(userUserName)
                    if (user == null) {
                        call.respond(HttpStatusCode.NotFound, "Usuario no encontrado")
                    } else {
                        val upUser = user.toUpdateUser()
                        upUser.msg = "User OK"
                        call.respond(upUser)
                    }
                    return@get
                }else{
                    val employees = ProviderUseCase.getAllUsers()
                    call.respond(employees)
                }

            }




            get("{userUserName}"){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validate = call.validateToken(token!!)
                if (!validate)
                    return@get

                val userUserName = call.parameters["userUserName"]
                if (userUserName == null){
                    call.respond(HttpStatusCode.BadRequest, "Debes pasar el nombre de usuario a buscar")
                    return@get
                }

                val user = ProviderUseCase.getUserByUserName(userUserName)
                if (user ==null){
                    call.respond(HttpStatusCode.NotFound,"Usuario no encontrado")
                    return@get
                }
                call.respond(user)
            }


            patch("{userUserName}"){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validate = call.validateToken(token!!)
                if (!validate)
                    return@patch

                try{
                    val userName = call.parameters["userUserName"]
                    userName?.let{
                        val updateUser = call.receive<UpdateUser>()
                        val user = ProviderUseCase.getUserByUserName(userName)
                        if (user != null) {
                            if (user.token == token) {
                                val res = ProviderUseCase.updateUser(updateUser, userName)
                                if (! res){
                                    call.respond(HttpStatusCode.Conflict, "El usuario no pudo modificarse. Puede que no exista")
                                    return@patch
                                }
                                call.respond(HttpStatusCode.Created, "Se ha actualizado correctamente con userName =  ${userName}")
                            } else {
                                call.respond(HttpStatusCode.BadRequest,"No puedes modificar usuarios ajenos")
                            }

                        } else {
                            call.respond(HttpStatusCode.NotFound,"No se ha encontrado usuario con ese nombre")
                        }
                    }?: run{
                        call.respond(HttpStatusCode.BadRequest,"Debes identificar el usuario logueado")
                        return@patch
                    }
                } catch (e: IllegalStateException){
                    call.respond(HttpStatusCode.BadRequest,"Error en el formato de envío de los datos o lectura del cuerpo.")
                } catch (e: JsonConvertException){
                    call.respond(HttpStatusCode.BadRequest,"Error en el formato de json")
                }
            }


            delete("{userUserName}"){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") //token el header
                val validate = call.validateToken(token!!)
                if (!validate)
                    return@delete

                val userName = call.parameters["userUserName"]
                ProviderUseCase.logger.warn("Queremos borrar el usuario con userName $userName")
                userName?.let{
                    val user = ProviderUseCase.getUserByUserName(userName)
                    if (user != null) {
                        if (user.token == token) {
                            val res = ProviderUseCase.deleteUser(userName)
                            if (! res){
                                call.respond(HttpStatusCode.NotFound,"Usuario no encontrado para borrar")
                            }else{
                                call.respond(HttpStatusCode.NoContent, "Usuario borrado correctamente")
                            }
                        } else {
                            call.respond(HttpStatusCode.BadRequest,"No puedes eliminar usuarios ajenos")
                        }
                    } else {
                        call.respond(HttpStatusCode.NotFound,"Usuario no encontrado")
                    }
                }?:run{
                    call.respond(HttpStatusCode.NoContent,"Debes identificar el usuario")
                }
                return@delete
            }

        }

    }


}