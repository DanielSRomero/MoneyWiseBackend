package domain.mapper

import data.persistence.models.UserDao
import domain.models.UpdateUser
import domain.models.User

fun User.toUpdateUser() : UpdateUser {
    return UpdateUser(
        name = name,
        userName = userName,
        email = email,
        password = password,
        phone = phone,
        token = token
    )
}


fun UpdateUser.toUser() : User {
    return User(
        name = name!!,
        userName = userName!!,
        password = password!!,
        email = email!!,
        phone = phone!!,
        token = token!!
    )
}

fun UserDao.toUser () : User {

    val e = User(
        this.name,
        this.userName,
        this.email,
        this.password,
        this.phone ?: "000-0000-0000",
        this.token ?: "null",
    )
    return e
}