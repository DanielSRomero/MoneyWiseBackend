package domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUser(
    var userName: String? = null,
    var password : String? = null,
    var name : String? = null,
    var email : String? = null,
    var phone: String? = null,
    var token:String ? = null,
    var msg:String? = null
)