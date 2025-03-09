package domain.usecase.goals

import domain.infraestructure.Utils
import domain.models.user.User
import domain.repository.GoalsInterface

class DeleteGoalUseCase (val repository : GoalsInterface) {
    var name: String? = null
    var user: User? = null

    suspend operator fun invoke(): Boolean {
        return if (name == null || user == null) {
            false
        } else {
            val goal = repository.getGoalByName(name!!, user!!)
            goal?.let { go ->
                go.image?.let{ img->
                    Utils.deleteImage(user!!.userName, img)
                }
                return repository.deleteGoal(name!!, user!!)
            }
            false
        }
    }
}