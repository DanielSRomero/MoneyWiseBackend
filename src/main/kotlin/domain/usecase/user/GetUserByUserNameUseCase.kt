package domain.usecase.user

import domain.models.user.User
import domain.repository.UserInterface

class GetUserByUserNameUseCase (val repository : UserInterface){
    var userName : String? = null


    suspend operator fun invoke() : User? {
        return if (userName?.isNullOrBlank() == true)
            null
        else{
            repository.getUserByUserName(userName!!)
        }
    }
}