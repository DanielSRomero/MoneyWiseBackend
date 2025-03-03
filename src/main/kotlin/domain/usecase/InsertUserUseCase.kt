package domain.usecase

import domain.models.User
import domain.repository.UserInterface

class InsertUserUseCase (val repository : UserInterface){
    var user : User? = null

    suspend operator fun invoke() : Boolean {

        return if (user == null) {
            false
        }else {
            repository.postUser(user!!)
        }
    }
}