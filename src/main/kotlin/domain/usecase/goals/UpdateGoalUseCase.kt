package domain.usecase.goals

import domain.infraestructure.Utils
import domain.models.goals.Goal
import domain.models.goals.UpdateGoal
import domain.models.user.User
import domain.repository.GoalsInterface

class UpdateGoalUseCase (val repository : GoalsInterface){
    var goal : UpdateGoal? = null
    var name : String? = null
    var user : User? = null

    suspend operator fun invoke() : Boolean {
        return if (user == null || goal == null || name == null) {
            false
        }else {
            try {
                goal?.image?.let{  newImg->//siempre que haya una nueva imagen a insertar.
                    //estoy dentro de la nueva imagen a crear.
                    val go = repository.getGoalByName(name!!, user!!)
                    go?.let { go ->
                        go.image?.let{ oldImg->  //Si hay imagen antigua, me la cargo
                            Utils.deleteImage(go.userUserName, oldImg)  //la elimino.
                        }
                    }
                    //ahora tengo que crear la nueva imagen.
                    val newImagenUrl = Utils.createBase64ToImg(newImg, user!!.userName)
                    goal!!.image = newImagenUrl
                }//fin de si hay nueva imagen a insertar.
                val newGoal = repository.updateGoal(goal!!, name!!, user!!)
                return newGoal
            }catch (e: Exception){
                e.printStackTrace()
                false
            }
        }
    }
}