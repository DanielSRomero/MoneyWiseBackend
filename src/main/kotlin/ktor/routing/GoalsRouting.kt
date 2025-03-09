package ktor.routing

import domain.models.goals.Goal
import domain.models.goals.UpdateGoal
import domain.models.user.User
import domain.usecase.ProviderUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import io.ktor.server.routing.*
import ktor.validateToken


fun Route.goalsRouting(){
    route ("/goals"){
        authenticate("jwt-auth"){
            get(){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validate = call.validateToken(token!!)
                if (!validate)
                    return@get

                val user: User? = ProviderUseCase.getAllUsers().find { it.token == token }
                if (user == null){
                    call.respond(HttpStatusCode.NotFound,"Error encontrando usuario")
                    return@get
                } else {
                    val goals  = ProviderUseCase.getGoalsFromUser(user.userName)
                    if (goals == null) {
                        call.respond(HttpStatusCode.NotFound,"Error encontrando metas de este usuario")
                        return@get
                    }
                    call.respond(goals)
                }
            }

            post(){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validate = call.validateToken(token!!)
                if (!validate)
                    return@post

                val user: User? = ProviderUseCase.getAllUsers().find { it.token == token }
                if (user == null){
                    call.respond(HttpStatusCode.NotFound,"Error encontrando usuario")
                    return@post
                } else {
                    try {
                        val goal = call.receive<Goal>()
                        val res = ProviderUseCase.insertGoal(goal, user.userName)
                        if (! res){
                            call.respond(HttpStatusCode.Conflict, "La meta no pudo insertarse. Puede que ya exista")
                            return@post
                        }
                        call.respond(HttpStatusCode.Created, "Se ha insertado correctamente la meta =  ${goal.name}")

                    } catch (e : IllegalStateException){
                        call.respond(HttpStatusCode.BadRequest, "Error en el formato de envío de datos o lectura del cuerpo.")
                    } catch (e: JsonConvertException){
                        call.respond(HttpStatusCode.BadRequest," Problemas en la conversión json")
                    } catch (e: Exception){
                        call.respond(HttpStatusCode.BadRequest, "Error en los datos. Probablemente falten.")
                    }
                }
            }

            patch("{goalName}"){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validate = call.validateToken(token!!)
                if (!validate)
                    return@patch

                val user: User? = ProviderUseCase.getAllUsers().find { it.token == token }
                if (user == null){
                    call.respond(HttpStatusCode.NotFound,"Error encontrando usuario")
                    return@patch
                } else{
                    try {
                        val name = call.parameters["goalName"]
                        name?.let{
                            val updateGoal = call.receive<UpdateGoal>()
                            val goalsOfUser = ProviderUseCase.getGoalsFromUser(user.userName)

                            if (goalsOfUser?.find { it.name == updateGoal.name } == null) {
                                val res = ProviderUseCase.updateGoal(updateGoal, name, user.userName)
                                if (! res){
                                    call.respond(HttpStatusCode.Conflict, "La meta no pudo modificarse. Puede que no exista")
                                    return@patch
                                }
                                call.respond(HttpStatusCode.Created, "Se ha actualizado correctamente la meta =  ${name}")
                            } else {
                                call.respond(HttpStatusCode.Conflict, "La meta no pudo modificarse. Este usuario ya tiene una meta con ese nombre")
                            }
                        }?: run{
                            call.respond(HttpStatusCode.BadRequest,"Debes identificar correctamente la meta del usuario")
                            return@patch
                    }
                    } catch (e: IllegalStateException){
                        call.respond(HttpStatusCode.BadRequest,"Error en el formato de envío de los datos o lectura del cuerpo.")
                    } catch (e: JsonConvertException){
                        call.respond(HttpStatusCode.BadRequest,"Error en el formato de json")
                    }
                }
            }

            delete("{name}"){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validate = call.validateToken(token!!)
                if (!validate)
                    return@delete

                val user: User? = ProviderUseCase.getAllUsers().find { it.token == token }
                if (user == null){
                    call.respond(HttpStatusCode.NotFound,"Error encontrando usuario")
                    return@delete
                } else{
                    val name = call.parameters["name"]
                    ProviderUseCase.logger.warn("Queremos borrar la meta de nombre $name")
                    name?.let {
                        val res = ProviderUseCase.deleteGoal(name, user.userName)
                        if (! res){
                            call.respond(HttpStatusCode.NotFound,"Meta no encontrada para borrar")
                        }else{
                            call.respond(HttpStatusCode.NoContent, "Meta borrada correctamente")
                        }
                    }?:run{
                        call.respond(HttpStatusCode.NoContent,"Debes identificar el usuario")
                    }
                    return@delete
                }
            }
        }
    }
}