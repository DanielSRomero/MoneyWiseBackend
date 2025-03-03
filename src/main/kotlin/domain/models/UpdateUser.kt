package domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUser(
    var name : String?,
    var userName: String?,
    var email : String?,
    var password : String?,
    var phone: String?,
    var token:String ? = null,
    var msg:String? = ""
)