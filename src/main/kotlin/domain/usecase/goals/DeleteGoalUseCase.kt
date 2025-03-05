package domain.usecase.goals

import domain.models.user.User
import domain.repository.GoalsInterface

class DeleteGoalUseCase (val repository : GoalsInterface) {
    var name: String? = null
    var user: User? = null

    suspend operator fun invoke(): Boolean {
        return if (name == null || user == null) {
            false
        } else {
            return repository.deleteGoal(name!!, user!!)
        }
    }
}