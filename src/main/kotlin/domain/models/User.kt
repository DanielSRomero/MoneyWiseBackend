package domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var userName: String,
    var password : String,
    var name : String,
    var email : String,
    var phone: String,
    var token:String ? = null
)