package domain.models.goals

import kotlinx.serialization.Serializable

@Serializable
data class UpdateGoal (
    var userUserName : String? = null,
    var name : String? = null,
    var money : Int? = null,
    var year : Int? = null,
    var image : String? = null
)