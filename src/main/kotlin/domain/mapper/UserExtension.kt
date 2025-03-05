package domain.mapper

import data.persistence.models.user.UserDao
import domain.models.user.UpdateUser
import domain.models.user.User

fun User.toUpdateUser() : UpdateUser {
    return UpdateUser(
        userName = userName,
        password = password,
        name = name,
        email = email,
        phone = phone,
        token = token
    )
}


fun UpdateUser.toUser() : User {
    return User(
        userName = userName!!,
        password = password!!,
        name = name!!,
        email = email!!,
        phone = phone!!,
        token = token!!
    )
}

fun UserDao.toUser () : User {

    val e = User(
        this.userName,
        this.password,
        this.name ?: "",
        this.email ?: "",
        this.phone ?: "000-0000-0000",
        this.token ?: "null",
    )
    return e
}