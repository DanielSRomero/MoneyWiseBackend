package domain.usecase

import domain.models.User
import domain.repository.UserInterface

class GetAllUsersUseCase (val repository : UserInterface){

    suspend operator fun invoke(): List<User> = repository.getAllUser()
}