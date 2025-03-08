package domain.models.goals

import kotlinx.serialization.Serializable

@Serializable
data class Goal (
    var userUserName : String,
    var name : String,
    var money : Int,
    var year : Int,
    var image : String? = null
)