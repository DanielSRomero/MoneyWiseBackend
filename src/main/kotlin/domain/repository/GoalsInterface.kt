package domain.repository

import domain.models.goals.Goal
import domain.models.goals.UpdateGoal
import domain.models.user.User

interface GoalsInterface {
    suspend fun getAllGoals () : List <Goal>

    suspend fun getGoalByName ( name : String, user: User) : Goal?

    suspend fun getGoalsByUserUserName (userUserName: String) : List<Goal>

    suspend fun postGoal(goal: Goal, user: User) : Boolean

    suspend fun updateGoal(goal: UpdateGoal, name: String, user: User) : Boolean

    suspend fun deleteGoal(name : String, user: User) : Boolean

}