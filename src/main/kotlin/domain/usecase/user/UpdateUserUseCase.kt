package domain.usecase.user

import domain.models.user.UpdateUser
import domain.repository.UserInterface

class UpdateUserUseCase (val repository : UserInterface){

    var updateUser: UpdateUser? = null
    var userName: String? = null

    suspend operator fun invoke() : Boolean {
        return if (updateUser == null || userName == null) {
            false
        }else{
            return repository.updateUser(updateUser!!, userName!!)
        }

    }
}