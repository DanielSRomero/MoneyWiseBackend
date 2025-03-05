package domain.usecase.user

import domain.models.user.User
import domain.repository.UserInterface

class GetAllUsersUseCase (val repository : UserInterface){

    suspend operator fun invoke(): List<User> = repository.getAllUser()
}