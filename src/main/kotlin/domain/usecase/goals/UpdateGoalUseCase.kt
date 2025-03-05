package domain.usecase.goals

import domain.models.goals.Goal
import domain.models.goals.UpdateGoal
import domain.models.user.User
import domain.repository.GoalsInterface

class UpdateGoalUseCase (val repository : GoalsInterface){
    var goal : UpdateGoal? = null
    var name : String? = null
    var user : User? = null

    suspend operator fun invoke() : Boolean {
        return if (user == null || goal == null || name == null) {
            false
        }else {
            repository.updateGoal(goal!!, name!!, user!!)
        }
    }
}