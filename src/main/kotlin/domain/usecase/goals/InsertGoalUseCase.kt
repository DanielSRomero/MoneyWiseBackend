package domain.usecase.goals

import domain.models.goals.Goal
import domain.models.user.User
import domain.repository.GoalsInterface

class InsertGoalUseCase (val repository : GoalsInterface){
    var goal : Goal? = null
    var user : User? = null

    suspend operator fun invoke() : Boolean {
        return if (user == null || goal == null) {
            false
        }else {
            repository.postGoal(goal!!, user!!)
        }
    }
}