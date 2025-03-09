package domain.usecase.goals

import domain.models.goals.Goal
import domain.models.user.User
import domain.repository.GoalsInterface
import domain.infraestructure.Utils
import ktor.ApplicationContext

class InsertGoalUseCase (val repository : GoalsInterface){
    var goal : Goal? = null
    var user : User? = null

    suspend operator fun invoke() : Boolean {
        return if (user == null || goal == null) {
            false
        }else {
            val isCreateDir = Utils.createDir(goal!!.userUserName)  //creamos su directorio
            if (isCreateDir){
                val img = goal!!.image
                if (!img.isNullOrBlank()){  //Si tiene imagen, hay que crearla.
                    goal!!.image = Utils.createBase64ToImg(img, goal!!.userUserName)  //creamos la imagen, a partir del Base64 y devolvemos su http
                }
            }else{
                throw IllegalStateException("No se pudo crear el directorio del usuario. Puede que ya exista")
            }

            val new = repository.postGoal(goal!!, user!!)  //insertamos el employee

            new?.let{ gl ->
                if (!gl.image.isNullOrBlank())   { //Debemos setear la url correctamente.
                    val local = ApplicationContext.context.environment.config.property("ktor.urlPath.baseUrl").getString()
                    val relativePath = ApplicationContext.context.environment.config.property("ktor.urlPath.images").getString()
                    new.image = "$local/$relativePath/${new?.userUserName}/${gl.image}"
                }
            }

            return true
        }
    }
}