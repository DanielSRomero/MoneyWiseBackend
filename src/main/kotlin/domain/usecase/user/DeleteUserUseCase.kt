package domain.usecase.user

import domain.repository.UserInterface

class DeleteUserUseCase (val repository : UserInterface){
    var userName : String? = null

    suspend operator fun invoke() : Boolean {
        return if (userName == null) {
            false
        }else{
            return repository.deleteUser(userName!!)
        }

    }
}