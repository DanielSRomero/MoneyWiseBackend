package domain.usecase.user

import domain.models.user.UpdateUser
import domain.models.user.User
import domain.repository.UserInterface

class RegisterUseCase (val repository : UserInterface){

    operator suspend fun invoke(user: UpdateUser): User? {

        user.userName = user.userName!!
        user.password = user.password!!
        user.name = user.name?:""
        user.phone = user.phone?:"0000000"
        user.email = user.email?:""
        user.token = user.token?: ""

        return if (repository.login(user.userName!!, user.password!!)!=null)
            null
        else
            repository.register(user)
    }
}