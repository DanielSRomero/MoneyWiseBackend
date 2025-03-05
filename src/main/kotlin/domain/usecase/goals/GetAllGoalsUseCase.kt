package domain.usecase.goals

import domain.models.goals.Goal
import domain.repository.GoalsInterface

class GetAllGoalsUseCase (val repository : GoalsInterface) {

    suspend operator fun invoke(): List<Goal> = repository.getAllGoals()

}