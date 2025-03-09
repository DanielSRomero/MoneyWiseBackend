package domain.usecase.user

import domain.infraestructure.Utils
import domain.repository.UserInterface

class DeleteUserUseCase (val repository : UserInterface){
    var userName : String? = null

    suspend operator fun invoke() : Boolean {
        return if (userName == null) {
            false
        }else{
            Utils.deleteDirectory(userName!!)
            return repository.deleteUser(userName!!)
        }

    }
}