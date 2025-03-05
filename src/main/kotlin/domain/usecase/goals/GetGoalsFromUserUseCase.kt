package domain.usecase.goals

import domain.models.goals.Goal
import domain.repository.GoalsInterface

class GetGoalsFromUserUseCase (val repository : GoalsInterface) {
    var userName : String? = null
    suspend operator fun invoke(): List<Goal>? {
        if (userName == null) return null
        return repository.getGoalsByUserUserName(userName!!)
    }

}