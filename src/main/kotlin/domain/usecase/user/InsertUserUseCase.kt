package domain.usecase.user

import domain.models.user.User
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